/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.model;

import com.gamehex.entity.GameContent;
import com.gamehex.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import com.gamehex.model.DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class GameContentDAO implements DAO<GameContent>{
    
    Connection cnx;
    PreparedStatement statement;
    private ResultSet rs;

    public GameContentDAO() {
        this.cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void Add(GameContent gc) {
        
        String request = "insert into info (contentTitle, infoContent) values ('" + gc.getTitle() + "','"+ gc.getVideoURL()+"')";
        
        try {
            statement = cnx.prepareStatement(request);
            statement.executeUpdate();
            System.out.println("Item added");
            
        } catch (SQLException ex) {
            System.out.println("not working");
            Logger.getLogger(GameContentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Update(GameContent gc) {
        String query = "UPDATE info SET contentTitle = '" + gc.getTitle() + "', infoContent = '" + gc.getVideoURL() + "' WHERE contentID = " + gc.getId_GameContent();

        try {
            statement = cnx.prepareStatement(query);

            if (statement.executeUpdate(query) > 0) {
                statement.executeUpdate();
                System.out.println("updated");
            }

        } catch (SQLException ex) {
            Logger.getLogger(GameContentDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update Failed");

        }
    }

    @Override
    public void Delete(GameContent gc) {
        String request = "delete from info where contentID=" + gc.getId_GameContent();
        GameContent gc1 = displayById(gc);

        if (gc1 != null) {
            try {
                statement = cnx.prepareStatement(request);
                statement.executeUpdate(request);

            } catch (SQLException ex) {
                Logger.getLogger(GameContentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }

    @Override
    public ObservableList<GameContent> ViewAll() {
        
        
        
        String request = "select * from info";
        ObservableList<GameContent> GameContentsList = FXCollections.observableArrayList();

        try {
            statement = cnx.prepareStatement(request);
            rs = statement.executeQuery(request);
            while (rs.next()) {
                GameContent gc = new GameContent();

                gc.setId_GameContent(rs.getInt(1));
                gc.setTitle(rs.getString("contentTitle"));
                gc.setVideoURL(rs.getString("infoContent"));
                gc.setUploadDate(rs.getDate("contentDate"));

                GameContentsList.add(gc);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GameContentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return GameContentsList;
        
        
    }

    //DONE BUT THE ID NEEDS TO BE DYNAMIC
    @Override
    public GameContent displayById(GameContent gc) {
        String request = "select * from info where contentID =" + gc.getId_GameContent();
        
        try {
            statement = cnx.prepareStatement(request);
            rs = statement.executeQuery(request);
            while(rs.next()){
            GameContent gc1 = new GameContent();
            gc1.setId_GameContent(rs.getInt("contentID"));
            gc1.setTitle(rs.getString("contentTitle"));
            gc1.setVideoURL(rs.getString("infoContent"));
            gc1.setUploadDate(rs.getDate("contentDate"));
            
            System.out.println(gc1);
        }  
        } catch (SQLException ex) {
            Logger.getLogger(GameContentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gc;
    }
}

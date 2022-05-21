/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.model;

import com.gamehex.entity.Tuto;
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
public class TutoDAO implements DAO<Tuto>{
    
    Connection cnx;
    PreparedStatement statement;
    private ResultSet rs;

    public TutoDAO() {
        this.cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void Add(Tuto gc) {
        
        String request = "insert into tutos (title, content) values ('" + gc.getTitle() + "','"+ gc.getTuto()+"')";
        
        try {
            statement = cnx.prepareStatement(request);
            statement.executeUpdate();
            System.out.println("Item added");
            
        } catch (SQLException ex) {
            System.out.println("not working");
            Logger.getLogger(TutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Update(Tuto gc) {
        String query = "UPDATE tutos SET title = '" + gc.getTitle() + "', content = '" + gc.getTuto() + "' WHERE tutoID = " + gc.getId_Tuto();

        try {
            statement = cnx.prepareStatement(query);

            if (statement.executeUpdate(query) > 0) {
                statement.executeUpdate();
                System.out.println("updated");
            }

        } catch (SQLException ex) {
            Logger.getLogger(TutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update Failed");

        }
    }

    @Override
    public void Delete(Tuto gc) {
        String request = "delete from tutos where tutoID=" + gc.getId_Tuto();
        Tuto gc1 = displayById(gc);

        if (gc1 != null) {
            try {
                statement = cnx.prepareStatement(request);
                statement.executeUpdate(request);

            } catch (SQLException ex) {
                Logger.getLogger(TutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }

    @Override
    public ObservableList<Tuto> ViewAll() {
        
        
        
        String request = "select * from tutos";
        ObservableList<Tuto> TutosList = FXCollections.observableArrayList();

        try {
            statement = cnx.prepareStatement(request);
            rs = statement.executeQuery(request);
            while (rs.next()) {
                Tuto gc = new Tuto();

                gc.setId_Tuto(rs.getInt(1));
                gc.setTitle(rs.getString("title"));
                gc.setTuto(rs.getString("content"));
                gc.setUploadDate(rs.getDate("Date"));

                TutosList.add(gc);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TutosList;
        
        
    }

    //DONE BUT THE ID NEEDS TO BE DYNAMIC
    @Override
    public Tuto displayById(Tuto gc) {
        String request = "select * from tutos where tutoID =" + gc.getId_Tuto();
        
        try {
            statement = cnx.prepareStatement(request);
            rs = statement.executeQuery(request);
            while(rs.next()){
            Tuto gc1 = new Tuto();
            gc1.setId_Tuto(rs.getInt("tutoID"));
            gc1.setTitle(rs.getString("title"));
            gc1.setTuto(rs.getString("content"));
            gc1.setUploadDate(rs.getDate("Date"));
            
            System.out.println(gc1);
        }  
        } catch (SQLException ex) {
            Logger.getLogger(TutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gc;
    }
}

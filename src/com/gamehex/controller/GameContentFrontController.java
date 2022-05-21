/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;
//gamehexlibrary.ViewContents.DisplayController
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.gamehex.entity.GameContent;
import com.gamehex.model.GameContentDAO;
import com.gamehex.utils.MyConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import com.gamehex.entity.GameContent;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class GameContentFrontController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private GridPane contentGrid;
        
    private List<GameContent> contents;    
    
    long millis=System.currentTimeMillis();  
    java.sql.Date date=new java.sql.Date(millis);  
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        contents = new ArrayList<>(getContents());
        int column = 0 ;
        int row = 1 ;
        try {
            for(GameContent content: contents){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource( "/com/gamehex/view/videoContent.fxml"));
                Pane pane = fxmlLoader.load();
                VideoContentController contentController = fxmlLoader.getController();
                contentController.setData(content);
                
                if(column ==3){
                    column = 0;
                    ++row;
                }
                
                contentGrid.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(20));
            }
        } catch (IOException ex) {
                ex.printStackTrace();

        } 
    }  
    

        
        PreparedStatement statement;
        private ResultSet rs;
        Connection cnx = MyConnection.getInstance().getCnx();
    
    
    
    public ObservableList<GameContent> getContents() {
        
        
        
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
    
    
    
    
    /*public void screen(){
        new Thread(){
        public void run(){
            
            try {
                borderPane = FXMLLoader.load(getClass().getResource("Display.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(borderPane);
                stage.setScene(scene);
                stage.setMinHeight(400);
                stage.setMinWidth(600);
                stage.show();
                } 
            catch (IOException ex) {
                Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
                    }.start();
    }*/
}

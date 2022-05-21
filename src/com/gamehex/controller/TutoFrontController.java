/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;
//gamehexlibrary.ViewContents.TutoFrontController
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
import com.gamehex.entity.Tuto;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import com.gamehex.controller.TTSController;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class TutoFrontController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private GridPane contentGrid;
        
    private List<Tuto> contents;    
    
    long millis=System.currentTimeMillis();  
    java.sql.Date date=new java.sql.Date(millis);  
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        contents = new ArrayList<>(getTuto());
        int column = 0 ;
        int row = 1 ;
        try {
            for(Tuto content: contents){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource( "/com/gamehex/view/Tuto.fxml"));
                Pane pane = fxmlLoader.load();
                TTSController TutoController = fxmlLoader.getController();
                TutoController.setData(content);
                
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
    
    public Connection getConnection(){
    Connection cnx;
    try{
        cnx = DriverManager.getConnection("jdbc:mysql://127.0.0.1/gamehex","root","");
        return cnx;
    }
    catch(Exception ex){
        System.out.println("Error: "+ex.getMessage());
        return null;
    }
    }
    
    public ObservableList<Tuto> getTuto(){
        ObservableList<Tuto> contentList = FXCollections.observableArrayList();
        Connection cnx = getConnection();
        String query = "SELECT * FROM tutos";
        Statement st;
        ResultSet rs;
        
        try{
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Tuto contents;
            while(rs.next()){
                contents = new Tuto(rs.getInt("tutoID"),rs.getString("title"),rs.getString("content"),rs.getDate("Date"));
                contentList.add(contents);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return contentList;
    }
    
    
/*private List<Tuto> getTuto(){
    List<Tuto> listTutos = new ArrayList<>();
    
    for (int i = 0; i < 4; i++) {
        Tuto Tuto = new Tuto();
    Tuto.setTitle("How top esports talents");
    Tuto.setTuto("With the popularity of esports exploding in India and improving tech infrastructure, a new generation of players hope that gaming will ...");
    Tuto.setUploadDate(date);
    listTutos.add(Tuto);
}

    return listTutos;
    }*/
    
    
    
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
                Logger.getLogger(TutoFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
                    }.start();
    }*/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import com.gamehex.model.GameContentDAO;
import com.gamehex.utils.MyConnection;
import com.gamehex.entity.GameContent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author HP
 */
public class GameContentController implements Initializable {
    
    @FXML
    private TextField txtID ;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtURL;
    @FXML
    private TextField txtDate;
    @FXML
    private TableView<GameContent> tableviewGC;
    @FXML
    private TableColumn<GameContent, Integer> colID;
    @FXML
    private TableColumn<GameContent, String> colTitle;
    @FXML
    private TableColumn<GameContent, String> colURL;
    @FXML
    private TableColumn<GameContent, Date> colDate;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if     (event.getSource() == btnInsert)     { add();    }
        else if(event.getSource() == btnUpdate)     { update(); }
        else if(event.getSource() == btnDelete)     { delete(); }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showContent();
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
    
    public ObservableList<GameContent> getContentList(){
        ObservableList<GameContent> contentList = FXCollections.observableArrayList();
        Connection cnx = getConnection();
        String query = "SELECT * FROM info";
        Statement st;
        ResultSet rs;
        
        try{
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            GameContent contents;
            while(rs.next()){
                contents = new GameContent(rs.getInt("contentID"),rs.getString("contentTitle"),rs.getString("infoContent"),rs.getDate("contentDate"));
                contentList.add(contents);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return contentList;
    }

    public void showContent(){
    ObservableList<GameContent> contentList = getContentList();
    
    colID.setCellValueFactory(new PropertyValueFactory<GameContent, Integer>("id_GameContent"));
    colTitle.setCellValueFactory(new PropertyValueFactory<GameContent, String>("title"));
    colURL.setCellValueFactory(new PropertyValueFactory<GameContent, String>("videoURL"));
    colDate.setCellValueFactory(new PropertyValueFactory<GameContent, Date>("uploadDate"));
    
    tableviewGC.setItems(contentList);
    }
    
    private void add(){
        String query = "insert into info (contentTitle, infoContent) values ('" + txtTitle.getText()+"','"+ txtURL.getText()+"')";
        executeQuery(query);
        showContent();
    }

    private void update(){
        String query="UPDATE info SET contentTitle = '" + txtTitle.getText() + "', infoContent = '" + txtURL.getText() + "', contentDate = '" + txtDate.getText() + "' WHERE contentID = " + txtID.getText()+"";
        executeQuery(query);
        showContent();        
    }
    
    private void delete(){
        String query="delete from info where contentID =" + txtID.getText();
        executeQuery(query);
        showContent();        
    }
    
    private void executeQuery(String query) {
        Connection cnx = getConnection();
        Statement st;
        try{
            st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        GameContent content = tableviewGC.getSelectionModel().getSelectedItem();
        txtID.setText("" + content.getId_GameContent());
        txtTitle.setText(content.getTitle());
        txtURL.setText(content.getVideoURL());
        txtDate.setText("" + content.getUploadDate());
    }
    
}

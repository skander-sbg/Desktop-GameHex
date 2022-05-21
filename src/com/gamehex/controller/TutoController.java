/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import com.gamehex.model.TutoDAO;
import com.gamehex.utils.MyConnection;
import com.gamehex.entity.Tuto;
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
public class TutoController implements Initializable {
    
    @FXML
    private TextField txtID ;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtURL;
    @FXML
    private TextField txtDate;
    @FXML
    private TableView<Tuto> tableviewGC;
    @FXML
    private TableColumn<Tuto, Integer> colID;
    @FXML
    private TableColumn<Tuto, String> colTitle;
    @FXML
    private TableColumn<Tuto, String> colURL;
    @FXML
    private TableColumn<Tuto, Date> colDate;
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
    
    public ObservableList<Tuto> getContentList(){
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
    


    public void showContent(){
    ObservableList<Tuto> contentList = getContentList();
    
    colID.setCellValueFactory(new PropertyValueFactory<Tuto, Integer>("id_Tuto"));
    colTitle.setCellValueFactory(new PropertyValueFactory<Tuto, String>("title"));
    colURL.setCellValueFactory(new PropertyValueFactory<Tuto, String>("Tuto"));
    colDate.setCellValueFactory(new PropertyValueFactory<Tuto, Date>("uploadDate"));
    
    tableviewGC.setItems(contentList);
    }
    
    private void add(){
        String query = "insert into tutos (title, content) values ('" + txtTitle.getText()+"','"+ txtURL.getText()+"')";
        executeQuery(query);
        showContent();
    }

    private void update(){
        String query="UPDATE tutos SET title = '" + txtTitle.getText() + "', content = '" + txtURL.getText() + "', Date = '" + txtDate.getText() + "' WHERE tutoID = " + txtID.getText()+"";
        executeQuery(query);
        showContent();        
    }
    
    private void delete(){
        String query="delete from tutos where tutoID =" + txtID.getText();
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
        Tuto content = tableviewGC.getSelectionModel().getSelectedItem();
        txtID.setText("" + content.getId_Tuto());
        txtTitle.setText(content.getTitle());
        txtURL.setText(content.getTuto());
        txtDate.setText("" + content.getUploadDate());
    }
    
}

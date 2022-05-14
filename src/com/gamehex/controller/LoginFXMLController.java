/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.gamehex.entity.User;
import com.gamehex.service.UsersService;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import com.gamehex.entity.Session;
import com.gamehex.entity.enumRole;
import com.gamehex.entity.enumRole.role;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private Hyperlink forget;
    @FXML
    private Button signin;
    @FXML
    private Button signup;
    @FXML
    private TextField tfLogEmail;
    @FXML
    private TextField tfLogPwd;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
    }   
    
    
     @FXML
    void signin(MouseEvent event) {
        User u = new User();
        UsersService us = new UsersService();
           if( (!tfLogEmail.getText().isEmpty()&& !tfLogPwd.getText().isEmpty()) ){
               if(us.checklogin(tfLogEmail.getText(), tfLogPwd.getText())==true){
                   //u.setEmail(tfLogEmail.getText());
                   u.setName(us.findName(tfLogEmail.getText()).getName());
                   u.setLastName(us.findName(tfLogEmail.getText()).getLastName());
                   u.setRole(us.findName(tfLogEmail.getText()).getRole());
                   /*if((("admin1@esprit.tn".equals(tfLogEmail.getText()))&&("admin1".equals(tfLogPwd.getText())))
                           ||(("admin2@esprit.tn".equals(tfLogEmail.getText()))&&("admin2".equals(tfLogPwd.getText()))))*/
                   if(u.getRole().equals(role.ADMIN.toString())){
                      try {
                           Session.StartSession().setSessionUser(us.existByEmail(tfLogEmail.getText()));
                           FXMLLoader fxmlLoader = new FXMLLoader();
                           Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/DashboardHome.fxml"));
                           Scene scene = new Scene(parent);
                           Stage stage = new Stage();
                           stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                           stage.setScene(scene);
                           stage.show();
                            } catch (IOException ex) {
                            System.out.println("error in displaying Admin Interface");
                           }
                   }
                   else if(u.getRole().equals(role.CLIENT.toString())){
                       try {
                            Session.StartSession().setSessionUser(us.existByEmail(tfLogEmail.getText()));
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/Home.fxml"));
                            Scene scene = new Scene(parent);
                            Stage stage = new Stage();
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                            } catch (IOException ex) {
                            System.out.println("error in displaying Client Interface");
                            }
                           }
                   else {
                       try {
                            Session.StartSession().setSessionUser(us.existByEmail(tfLogEmail.getText()));
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/Home.fxml"));
                            Scene scene = new Scene(parent);
                            Stage stage = new Stage();
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                            } catch (IOException ex) {
                            System.out.println("error in displaying Co Interface");
                            }
                       
                   }
                } 
               else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("User not found !!!  ");
                    alert.setContentText("Please verify your password or mail !!! ");
                    alert.show();
                   }
               
           }
           else{
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("User not found !!! ");
               alert.setContentText("Please fill in the filds !!! ");
               alert.show();
           }
              
    }

    
    @FXML
    private void signup(MouseEvent event) {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/signupFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying Article");
            }
    }

    @FXML
    private void forget(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/pwdFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying Article");
            }
        
    }
    
   
    
    }
  
    
    


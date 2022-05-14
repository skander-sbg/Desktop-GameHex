/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.gamehex.entity.Session;
import com.gamehex.entity.User;
import com.gamehex.service.UsersService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProfileFXMLController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfCIN;
    @FXML
    private TextField tfPhone;
    @FXML
    private DatePicker tfDate;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPWD;
    @FXML
    private Button update;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    
    User u=Session.StartSession().getSessionUser();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
   tfName.setText(u.getName());
   tfLastName.setText(u.getLastName());
   tfCIN.setText(String.valueOf(u.getCIN()));
   tfPhone.setText(String.valueOf(u.getPhone()));
   tfEmail.setText(u.getEmail());
   tfPWD.setText(u.getPwd());
   System.out.println(u);
        
        update.setOnAction(event->{
             if( (tfName.getText().isEmpty() || tfLastName.getText().isEmpty() || tfCIN.getText().isEmpty()
                   ||tfPhone.getText().isEmpty() || tfDate.getValue()==null
                   || tfEmail.getText().isEmpty() || tfPWD.getText().isEmpty()) ){
               
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("User can not be updated !!! ");
                  alert.setContentText("Please fill in the filds !!! ");
                  alert.show();
              }
             else if((!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", tfCIN.getText()))
                   || (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", tfPhone.getText()))){
                
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("User can not be updated !!! ");
                   alert.setContentText("CIN and Phone must be formed with numbers only ( 8 numbers to be specific) !!! ");
                   alert.show(); 
               }
             else if(!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", tfEmail.getText())){
                
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("User can not be updated !!! ");
                   alert.setContentText("Email format is not correct !!! ");
                   alert.show(); 
               }
               else{
                     Session.StartSession().setSessionUser(u);
                     UsersService us = new UsersService();    
                     int CIN = Integer.parseInt(tfCIN.getText());
                     int phone = Integer.parseInt(tfPhone.getText());
            
                     u.setName(tfName.getText());
                     u.setLastName(tfLastName.getText());
                     u.setCIN(CIN);
                     u.setPhone(phone);
                     u.setDate(tfDate.getValue().toString());
                     u.setEmail(tfEmail.getText());
                     u.setPwd(tfPWD.getText());
                     System.out.println(u);
                     us.updateUser(u);
            
                     tfName.setText("");
                     tfLastName.setText("");
                     tfCIN.setText("");
                     tfPhone.setText("");
                     //tfDate.setValue("");
                     tfEmail.setText("");
                      tfPWD.setText("");
            
                        try {
                             FXMLLoader fxmlLoader = new FXMLLoader();
                             Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/ArticleFXML.fxml"));
                             Scene scene = new Scene(parent);
                             Stage stage = new Stage();
                             stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                             stage.setScene(scene);
                             stage.show();
                             } catch (IOException ex) {
                             System.out.println("error in displaying the interface");
                             }  
                     }  
        });  
    }    

    @FXML
    private void back(MouseEvent event) {
        try { 
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/ArticleFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying the interface");
            }  
    }
      
    
}

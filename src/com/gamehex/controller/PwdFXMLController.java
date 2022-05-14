/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.gamehex.utils.MyConnection;
import com.gamehex.entity.User;
import com.gamehex.service.UsersService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class PwdFXMLController implements Initializable {

    @FXML
    private TextField tfReset;
    @FXML
    private Button reset;
    @FXML
    private Button back;
    @FXML
    private Button check;
    @FXML
    private TextField tfCheck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    Connection cnx2;
    public PwdFXMLController(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    @FXML
    private void back(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/loginFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying the interface");
            }  
    }

    
    @FXML
    private void check(MouseEvent event) {
        UsersService us=new UsersService();
         if(!tfCheck.getText().isEmpty()){
            if(us.existByEmail(tfCheck.getText())!=null){
                try {
                    javaMail.sendMail(tfCheck.getText());
                    } catch (Exception ex) {
                    Logger.getLogger(PwdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                /*Image img = new Image("/pics/accept.png");
                Notifications notificationBuilder = Notification.create()
                        .title("Email Checked")
                        .text("YOU can proceed to changing your password")
                        .graphic(new ImageView(img))
                        .hipeAfter(Duration.seconds(5))
                        .position(Pos.BASELINE_CENTER)
                        .onAction(new EventHandler<ActionEvent>(){
                            
                            
                        });
                            
                        
                notificationBuilder.darkStyle();
                notificationBuilder.show();*/
                System.out.println("YOU can procceed to changing your password");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Email not correct !!! ");
                alert.setContentText("Please verify your EMAIL !!! ");
                alert.show();
                }
            
         }
         
          else{
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Email not entered !!! ");
               alert.setContentText("Please fill in the EMAIL fild !!! ");
               alert.show();
               }
}
    
    @FXML
    private void reset(MouseEvent event) {
        UsersService us = new UsersService();
         if(!tfReset.getText().isEmpty()){
            User user = new User(us.existByEmail(tfCheck.getText()).getUserID(),us.existByEmail(tfCheck.getText()).getName(),us.existByEmail(tfCheck.getText()).getLastName(),
                                 us.existByEmail(tfCheck.getText()).getCIN(),us.existByEmail(tfCheck.getText()).getPhone(),us.existByEmail(tfCheck.getText()).getDate(),
                                 us.existByEmail(tfCheck.getText()).getEmail(),tfReset.getText());
            us.changePWD(user);
            try {
                 FXMLLoader fxmlLoader = new FXMLLoader();
                 Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/loginFXML.fxml"));
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

    
    
}

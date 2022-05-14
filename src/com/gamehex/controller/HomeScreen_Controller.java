/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import com.gamehex.entity.Session;
import com.gamehex.entity.User;
import com.gamehex.entity.enumRole;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Moudhaffer
 */
public class HomeScreen_Controller implements Initializable {

    @FXML
    private JFXButton btn_overlay;
    @FXML
    private JFXButton btn_coach;
    @FXML
    private JFXButton btn_sessions;

    
    
    User user = Session.StartSession().getSessionUser();
    int UID=0;
    String role="";

    public HomeScreen_Controller() {
        
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void handlebtn(ActionEvent event) {
        if(event.getSource() == btn_overlay){
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/com/gamehex/view/getSummoner.fxml"));
                //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
                Scene scene = new Scene(root);
                stage.setTitle("Summoner Information");
                stage.setScene(scene);
                stage.show();
                Stage stage2 = (Stage) btn_overlay.getScene().getWindow();
                stage2.close();
            } catch (IOException ex) {
                Logger.getLogger(HomeScreen_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == btn_coach){
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/com/gamehex/view/becomeCoach_UI.fxml"));
                //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
                Scene scene = new Scene(root);
                stage.setTitle("Become a Coach");
                stage.setScene(scene);
                stage.show();
                Stage stage2 = (Stage) btn_coach.getScene().getWindow();
                stage2.close();
            } catch (IOException ex) {
                Logger.getLogger(HomeScreen_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(event.getSource() == btn_sessions){
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/com/gamehex/view/createSession_UI_1.fxml"));
                //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
                Scene scene = new Scene(root);
                stage.setTitle("Schedule a Coaching Session");
                stage.setScene(scene);
                stage.show();
                Stage stage2 = (Stage) btn_sessions.getScene().getWindow();
                stage2.close();
            } catch (IOException ex) {
                Logger.getLogger(HomeScreen_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

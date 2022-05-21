/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import com.gamehex.entity.Tuto;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.Group;  
import javafx.scene.Scene;  
import javafx.scene.control.Button;
import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;  
import javafx.scene.media.MediaView; 
import javafx.stage.Stage;
import com.sun.speech.freetts.Voice;  
import com.sun.speech.freetts.VoiceManager;  
/**
 * FXML Controller class
 *
 * @author HP
 */
public class TTSController implements Initializable {

    @FXML
    private Label TutoTxt;

    @FXML
    private Label title;

    @FXML
    private Label date;    
    
    @FXML
    private Button btnRead;
    
    
    public void setData(Tuto Tuto){

    TutoTxt.setText(Tuto.getTuto());
    title.setText(Tuto.getTitle());
    date.setText(Tuto.getUploadDate()+"");
    }
    
        @FXML
    private void handleButtonAction(ActionEvent event) {
        if     (event.getSource() == btnRead)     { 
        //creating an object of the Voice class  
Voice voice;  
//getting voice, here we have used kevin (male version) voice  
voice = VoiceManager.getInstance().getVoice("kevin16");  

//the Voice class allocate() method allocates this voice  
voice.allocate();  

//sets the rate (words per minute i.e. 190) of the speech  
voice.setRate(130);  
//sets the baseline pitch (150) in hertz   
voice.setPitch(115);  
//sets the volume (10) of the voice   
voice.setVolume(100);   
//the speak() method speaks the specified text  
voice.speak(TutoTxt+"");  

        
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

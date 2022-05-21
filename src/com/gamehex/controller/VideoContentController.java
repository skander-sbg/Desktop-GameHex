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
import com.gamehex.entity.GameContent;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.Group;  
import javafx.scene.Scene;  
import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;  
import javafx.scene.media.MediaView; 
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class VideoContentController implements Initializable {


    @FXML
    private MediaView imgvideo;

    @FXML
    private Label title;

    @FXML
    private Label date;    
    private MediaPlayer mp;
    
    public void setData(GameContent gamecontent){
        //Instantiating Media class  
    Media media = new Media(new File(gamecontent.getVideoURL()).toURI().toString());
         
        //Instantiating MediaPlayer class   
        mp = new MediaPlayer(media);  
        
        
          
 
          
  

    imgvideo.setMediaPlayer(mp);
    title.setText(gamecontent.getTitle());
    date.setText(gamecontent.getUploadDate()+"");
    }
    
    @FXML
    public void play(ActionEvent event){
    mp.play();
    }
    @FXML
    public void pause(ActionEvent event){
    mp.pause();
    }
    @FXML
    public void reload(ActionEvent event){
    mp.seek(mp.getStartTime());
    mp.stop();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
}

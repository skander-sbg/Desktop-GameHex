/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Yasmine Daly
 */
public class SceneController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private TextField textField;

    private WebEngine engine;
    
    private String homePage;
    private double webZoom;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        engine=  webView.getEngine();
        homePage= "www.metasrc.com/valorant/stats/weapons.com";
        textField.setText(homePage);
        webZoom=1;
        loadPage();
        // TODO
    }    

    @FXML
    private void loadPage(ActionEvent event) {
        engine.load("http://"+textField.getText());
        
    }
    
     public void loadPage() {
         
         engine.load("http://" +textField.getText());
         
        
    }

    @FXML
    private void refreshPage(ActionEvent event) {
        
        engine.reload();
    }

    @FXML
    private void zoomIn(ActionEvent event) {
        webZoom+=0.25;
        webView.setZoom(webZoom);
    }

    @FXML
    private void zoomOut(ActionEvent event) {
        webZoom-=0.25;
        webView.setZoom(webZoom);
    }
    
    
     
     
    
}

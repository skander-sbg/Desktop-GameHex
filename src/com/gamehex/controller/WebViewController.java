/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author iHoussem
 */
public class WebViewController implements Initializable {

    @FXML
    private WebView mapView;
    private WebEngine engine;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       engine=mapView.getEngine();
        try {
            loadPage();// TODO
//       engine.load("file:///C:\\map\\sample.html");
//        if (engine.getDocument()==null) {
//            System.out.println("saj");
//        }
//        DOMDocument doc =(DOMDocument) engine.getDocument();
//        DOMElement adress=doc.findElement(By.id("pac-input"));
//        DOMInputElement adresss=(DOMInputElement) adress;
//        adress.setTextContent(nom);
//        adress.click();
        } catch (IOException ex) {
            Logger.getLogger(WebViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    public void loadPage() throws IOException{
        
        File htmlTemplateFile = new File("C:\\map\\index.js");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile);
        String data = "\""+TeamsFrontController.regionMap +" ,Near Tunisia \"";
        
        htmlString = htmlString.replace("$data", data);
        File newHtmlFile = new File("C:\\map\\new.js");
        FileUtils.writeStringToFile(newHtmlFile, htmlString);
        engine.load("file:///C:\\map\\sample.html");
    }
}
    
   

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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.gamehex.service.JavaMailUtil;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yasmine Daly
 */
public class SendMailController implements Initializable {

    @FXML
    private TextField tfRecepient;
    @FXML
    private TextField tfSubject;
    @FXML
    private TextField tfText;
    @FXML
    private Button btnSend;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public TextField getTfRecepient() {
        return tfRecepient;
    }

    public TextField getTfSubject() {
        return tfSubject;
    }

    public TextField getTfText() {
        return tfText;
    }

    @FXML
    private void onSendClick(MouseEvent event) {
        
        JavaMailUtil mail = new JavaMailUtil();
        String reciepent = tfRecepient.getText();
        String subject = tfSubject.getText();
        String text = tfText.getText();
        try{
        mail.sendMail( reciepent, subject, text);
        }catch( Exception e){
            e.printStackTrace();
        }
        
        Stage stage = (Stage) btnSend.getScene().getWindow();
        stage.close();
    }
    
}

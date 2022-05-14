/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tempestdesign.sendclient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Yasmine Daly
 */
public class LayEditController implements Initializable {

    @FXML
    private GridPane layEDIT;
    @FXML
    private ComboBox<?> cmbTYPE;
    @FXML
    private TextField tto;
    @FXML
    private TextField thead;
    @FXML
    private TextField tsub;
    @FXML
    private TextArea ttext;
    @FXML
    private VBox vb;
    @FXML
    private Button btnSEND;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSendButton(ActionEvent event) {
    }
    
}

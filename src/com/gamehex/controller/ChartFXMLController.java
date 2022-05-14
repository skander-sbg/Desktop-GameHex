/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.gamehex.entity.User;
import com.gamehex.service.UsersService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ChartFXMLController implements Initializable {

    @FXML
    private PieChart chart;
    private ObservableList<Data> list=FXCollections.
            observableArrayList();
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         UsersService us = new UsersService();
        List<User> ulist =us.displayUser();
        for(User u:ulist) {
            list.addAll(
                new PieChart.Data(u.getName(), u.getName().length()*0.01)             
        );
        }
        chart.setAnimated(true);
        chart.setData(list);
        
        
        back.setOnAction(event->{
            try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/UserFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying the interface");
            }  
        });
        
        
    }    
    
}

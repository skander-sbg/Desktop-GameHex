/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.gamehex.entity.Coaches;
import com.gamehex.entity.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import com.gamehex.utils.MyConnection;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Moudhaffer
 */
public class Coach_AdminUIController implements Initializable {

    @FXML
    private Button btn_delete;

    //Singleton connection
    Connection cnx;
    @FXML
    private Label label;
    @FXML
    private TableColumn<Coaches, Integer> col_rating;
    @FXML
    private TableView<Coaches> tv_coaches;
    @FXML
    private TableColumn<Coaches, SimpleIntegerProperty> col_id;
    @FXML
    private JFXTextField keywordTextField;
    @FXML
    private JFXComboBox<String> cb_ID;
    @FXML
    private Label label_warning;
    @FXML
    private JFXButton btn_viewAll;
    @FXML
    private JFXButton btnHome;
    /**
     * Initializes the controller class.
     */

    public Coach_AdminUIController() {
        this.cnx = MyConnection.getInstance().getCnx();
    }

    public void deleteCoach() {
        String query = "DELETE FROM coach WHERE coachId=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(cb_ID.getValue()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        showCoaches();
    }
    
        public void showCoachById(){

        ObservableList<Coaches> usernameList = FXCollections.observableArrayList();
        cb_ID.getItems().clear();
        String query = "SELECT coachId FROM coach";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query); 
           
            while(rs.next()){
                cb_ID.getItems().addAll(rs.getString("coachId"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void showCoaches() {

        ObservableList<Coaches> list = getCoachList();

        //Setting ID field value
        col_id.setCellValueFactory(cellData -> new SimpleObjectProperty(Integer.toString(cellData.getValue().getCoachId())));
        //Setting Rating field
        col_rating.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRating()));

        tv_coaches.setItems(list);

        FilteredList<Coaches> filteredData = new FilteredList<>(list, b -> true);
        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Coaches -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (String.valueOf(Coaches.getCoachId()).contains(searchKeyword)) {
                    return true;
                } else if (Coaches.getRating().toString().contains(searchKeyword)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Coaches> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tv_coaches.comparatorProperty());
        tv_coaches.setItems(sortedData);
    }
    
    public ObservableList<Coaches> getCoachList() {
        ObservableList<Coaches> coachList = FXCollections.observableArrayList();
        String query = "SELECT * FROM coach";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            Coaches coach;
            while (rs.next()) {
                coach = new Coaches(rs.getInt("coachId"), rs.getFloat("rating"));
                coachList.add(coach);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return coachList;
    }
    
    private boolean validateID() {
        if (cb_ID.getValue() != null) {
            cb_ID.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(cb_ID).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            cb_ID.setEffect(in);
            return false;
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btn_delete){
            deleteCoach();
            showCoachById();
        }
        if (event.getSource() == btn_viewAll){
            showCoaches();
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //showCoaches();
        showCoachById();
    }

    @FXML
    private void OnHomeClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/DashboardHome.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Home");
        //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

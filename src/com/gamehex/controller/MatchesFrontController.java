/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import com.gamehex.entity.Matches;
import com.gamehex.utils.MyConnection;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yasmine Daly
 */
public class MatchesFrontController implements Initializable {

    @FXML
    private TableView<Matches> tvMatches;
    @FXML
    private TableColumn<Matches, Integer> colMatchId;
    @FXML
    private TableColumn<Matches, Integer> colTeam1;
    @FXML
    private TableColumn<Matches, Integer> colTeam2;
    @FXML
    private TableColumn<Matches, String> colMatchRes;
    @FXML
    private TableColumn<Matches, String> colMatchCom;
    @FXML
    private TableColumn<Matches, Time> colMatchTime;
    @FXML
    private TableColumn<Matches, Date> colMatchDate;
    @FXML
    private TextField keywordTextField;
    @FXML
    private JFXButton btnHome;

    public TableColumn<Matches, Integer> getColMatchId() {
        return colMatchId;
    }

    public TableColumn<Matches, Integer> getColTeam1() {
        return colTeam1;
    }

    public TableColumn<Matches, Integer> getColTeam2() {
        return colTeam2;
    }

    public TableColumn<Matches, String> getColMatchRes() {
        return colMatchRes;
    }

    public TableColumn<Matches, String> getColMatchCom() {
        return colMatchCom;
    }

    public TableColumn<Matches, Time> getColMatchTime() {
        return colMatchTime;
    }

    public TableColumn<Matches, Date> getColMatchDate() {
        return colMatchDate;
    }
    private Connection conn;

    /**
     * Initializes the controller class.
     */
    
    
    public MatchesFrontController() {
        conn=MyConnection.getInstance().getCnx();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showMatches();
    }

    public ObservableList<Matches> getMatchesList() {

        String query = "SELECT * FROM matches";
        ObservableList<Matches> list = FXCollections.observableArrayList();
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Matches matches;
            while (rs.next()) {
                matches = new Matches(rs.getInt("id"), rs.getInt("team1_id"), rs.getInt("team2_id"), rs.getString("match_res"), rs.getString("match_com"), rs.getDate("match_date").toLocalDate(), rs.getTime("match_time").toLocalTime());
                list.add(matches);
            }

        } catch (SQLException ex) {
        }
        return list;
    }

    public void showMatches() {
        ObservableList<Matches> matchList = getMatchesList();

        getColMatchId().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getId()));

        getColTeam1().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTeam1_id()));

        getColTeam2().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTeam2_id()));

        getColMatchRes().setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMatch_res()));

        getColMatchCom().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getMatch_com()));

        getColMatchDate().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getMatch_date().toString()));

        getColMatchTime().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getMatch_time().toString()));

        tvMatches.setItems(matchList);

        FilteredList<Matches> filteredData;
        filteredData = new FilteredList<>(matchList, b -> true);
        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Matches -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (String.valueOf(Matches.getId()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(Matches.getTeam1_id()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(Matches.getTeam2_id()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(Matches.getMatch_res()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Matches.getMatch_com().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(Matches.getMatch_date()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(Matches.getMatch_time()).indexOf(searchKeyword) > -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Matches> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvMatches.comparatorProperty());
        tvMatches.setItems(sortedData);

    }

    @FXML
    private void OnHomeClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/Home.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Home");
        //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

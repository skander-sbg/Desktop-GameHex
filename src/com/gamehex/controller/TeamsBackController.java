/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import static com.gamehex.controller.TeamsFrontController.regionMap;
import com.gamehex.entity.Teams;
import com.gamehex.utils.MyConnection;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.Label;
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
public class TeamsBackController implements Initializable {

    @FXML
    private TableView<Teams> tvTeams;
    @FXML
    private TableColumn<Teams, Integer> colTeamId;
    @FXML
    private TableColumn<Teams, String> colTeamName;
    @FXML
    private TableColumn<Teams, String> colTeamTag;
    @FXML
    private TableColumn<Teams, String> colTeamMail;
    @FXML
    private TableColumn<Teams, String> colTeamReg;
    @FXML
    private TextField keywordTextField;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnPlayers;
    @FXML
    private JFXButton btnHome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTeams();
        // TODO
    }
    
    public static Integer teamId;
    private Connection conn;

    public TeamsBackController() {
        conn = MyConnection.getInstance().getCnx();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        
         if (event.getSource() == btnDelete) {
                deleteTeam();

            }
    }

    @FXML
    private void handleButtonPlayers(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent etab = FXMLLoader.load(getClass().getResource("/com/gamehex/view/TeamMembers.fxml"));
            //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
            Scene scene = new Scene(etab);
            stage.setTitle("Team Members");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
        }
    }

    

    public TableColumn<Teams, Integer> getColTeamId() {
        return colTeamId;
    }

    public TableColumn<Teams, String> getColTeamName() {
        return colTeamName;
    }

    public TableColumn<Teams, String> getColTeamTag() {
        return colTeamTag;
    }

    public TableColumn<Teams, String> getColTeamMail() {
        return colTeamMail;
    }

    public TableColumn<Teams, String> getColTeamReg() {
        return colTeamReg;
    }

    public ObservableList<Teams> getTeamsList() {

        String query = "SELECT * FROM teams ";
        ObservableList<Teams> list = FXCollections.observableArrayList();
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Teams teams;
            while (rs.next()) {
                teams = new Teams(rs.getInt("teamId"), rs.getString("teamName"), rs.getString("teamTag"), rs.getString("teamMail"), rs.getString("teamReg"));
                list.add(teams);
            }

        } catch (SQLException ex) {
        }
        return list;
    }

    public void showTeams() {
        ObservableList<Teams> teamList = getTeamsList();

        getColTeamId().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTeamId()));

        getColTeamName().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTeamName()));

        getColTeamTag().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTeamTag()));

        getColTeamMail().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTeamMail()));

        getColTeamReg().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTeamReg()));

        tvTeams.setItems(teamList);

        FilteredList<Teams> filteredData;
        filteredData = new FilteredList<>(teamList, b -> true);
        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Teams -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (String.valueOf(Teams.getTeamId()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Teams.getTeamName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Teams.getTeamTag().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Teams.getTeamMail().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (Teams.getTeamReg().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Teams> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvTeams.comparatorProperty());
        tvTeams.setItems(sortedData);

    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Teams team = tvTeams.getSelectionModel().getSelectedItem();

        System.out.println("Id" + team.getTeamId());
        System.out.println("Name" + team.getTeamName());
        System.out.println("Tag" + team.getTeamTag());
        System.out.println("Mail" + team.getTeamMail());
        System.out.println("region" + team.getTeamReg());
        teamId = team.getTeamId();
    }
    
    
     private void deleteTeam() {
        String query = "DELETE FROM teams WHERE teamId=" + teamId + "";
        executeQuery(query);
        String query1 = "DELETE FROM teamMembers WHERE teamId=" + teamId + "";
        executeQuery(query1);
        showTeams();
    }
     
      private void executeQuery(String query) {

        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
        }
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

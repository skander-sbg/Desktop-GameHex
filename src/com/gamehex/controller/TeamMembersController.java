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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.gamehex.entity.TeamMembers;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import com.gamehex.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Yasmine Daly
 */
public class TeamMembersController implements Initializable {

    @FXML
    private TableView<TeamMembers> tvMembers;
    @FXML
    private TableColumn<TeamMembers, Integer> colRiotId;
    @FXML
    private TableColumn<TeamMembers, String> colMemberRole;
    @FXML
    private TableColumn<TeamMembers, Integer> colMemberPh;
    @FXML
    private TableColumn<TeamMembers, String> colMemberMail;
    @FXML
    private TableColumn<TeamMembers, Integer> colTeamIdd;
    @FXML
    private TextField keywordTextFieldd;

    public TableColumn<TeamMembers, Integer> getColRiotId() {
        return colRiotId;
    }

    public TableColumn<TeamMembers, String> getColMemberRole() {
        return colMemberRole;
    }

    public TableColumn<TeamMembers, Integer> getColMemberPh() {
        return colMemberPh;
    }

    public TableColumn<TeamMembers, String> getColMemberMail() {
        return colMemberMail;
    }

    public TableColumn<TeamMembers, Integer> getColTeamIdd() {
        return colTeamIdd;
    }
private Connection conn;

    

    public TeamMembersController() {
        conn=MyConnection.getInstance().getCnx();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showMembers();
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        TeamMembers members = tvMembers.getSelectionModel().getSelectedItem();
         System.out.println("RiotId" + members.getRiotId());
        System.out.println("Role" + members.getMemberRole());
        System.out.println("Phone" + members.getMemberPh());
        System.out.println("Mail" + members.getMemberMail());
        System.out.println("Team Id" + members.getTeamId());
//        TeamMembersController.riot1.setText("" + members.getRiotId());
//        TeamMembersController.role1.setText("" + members.getMemberRole());
//        TeamMembersController.memberPh1.setText("" + members.getMemberPh());
//        TeamMembersController.memberMail1.setText("" + members.getMemberMail());

    }
    
     public ObservableList<TeamMembers> getMembersList() {
        
        String query = "SELECT * FROM teamMembers ";
        ObservableList<TeamMembers> list = FXCollections.observableArrayList();
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            TeamMembers members;
            while (rs.next()) {
                members = new TeamMembers(rs.getInt("riotId"), rs.getString("memberRole"), rs.getInt("memberPhone"), rs.getString("memberMail"), rs.getInt("teamId"));
                list.add(members);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void showMembers() {
        ObservableList<TeamMembers> membersList = getMembersList();

        getColRiotId().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRiotId()));

        getColMemberRole().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getMemberRole()));

        getColMemberPh().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getMemberPh()));

        getColMemberMail().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getMemberMail()));

        getColTeamIdd().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTeamId()));

        tvMembers.setItems(membersList);

        FilteredList<TeamMembers> filteredData;
        filteredData = new FilteredList<>(membersList, b -> true);
        keywordTextFieldd.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(TeamMembers -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (String.valueOf(TeamMembers.getRiotId()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (TeamMembers.getMemberRole().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(TeamMembers.getMemberPh()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (TeamMembers.getMemberMail().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(TeamMembers.getTeamId()).indexOf(searchKeyword) > -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<TeamMembers> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvMembers.comparatorProperty());
        tvMembers.setItems(sortedData);

    }
   
}

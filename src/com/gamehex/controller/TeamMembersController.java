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
import com.gamehex.entity.Team_mates;
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
    private TableView<Team_mates> tvMembers;
    @FXML
    private TableColumn<Team_mates, Integer> colRiotId;
    @FXML
    private TableColumn<Team_mates, String> colMemberRole;
    @FXML
    private TableColumn<Team_mates, Integer> colMemberPh;
    @FXML
    private TableColumn<Team_mates, String> colMemberMail;
    @FXML
    private TableColumn<Team_mates, Integer> colTeamIdd;
    @FXML
    private TextField keywordTextFieldd;

    public TableColumn<Team_mates, Integer> getColRiotId() {
        return colRiotId;
    }

    public TableColumn<Team_mates, String> getColMemberRole() {
        return colMemberRole;
    }

    public TableColumn<Team_mates, Integer> getColMemberPh() {
        return colMemberPh;
    }

    public TableColumn<Team_mates, String> getColMemberMail() {
        return colMemberMail;
    }

    public TableColumn<Team_mates, Integer> getColTeamIdd() {
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
        Team_mates members = tvMembers.getSelectionModel().getSelectedItem();
         System.out.println("RiotId" + members.getRiotId());
        System.out.println("Role" + members.getMember_role());
        System.out.println("Phone" + members.getMemberPh());
        System.out.println("Mail" + members.getMember_mail());
        System.out.println("Team Id" + members.getTeam_id());
//        TeamMembersController.riot1.setText("" + members.getRiotId());
//        TeamMembersController.role1.setText("" + members.getMemberRole());
//        TeamMembersController.memberPh1.setText("" + members.getMemberPh());
//        TeamMembersController.memberMail1.setText("" + members.getMemberMail());

    }
    
     public ObservableList<Team_mates> getMembersList() {
        
        String query = "SELECT * FROM team_mates ";
        ObservableList<Team_mates> list = FXCollections.observableArrayList();
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Team_mates members;
            while (rs.next()) {
                members = new Team_mates(rs.getInt("riot_id"), rs.getString("member_role"), rs.getInt("member_phone"), rs.getString("member_mail"), rs.getInt("team_id"));
                list.add(members);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void showMembers() {
        ObservableList<Team_mates> membersList = getMembersList();

        getColRiotId().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRiotId()));

        getColMemberRole().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getMember_role()));

        getColMemberPh().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getMemberPh()));

        getColMemberMail().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getMember_mail()));

        getColTeamIdd().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getTeam_id()));

        tvMembers.setItems(membersList);

        FilteredList<Team_mates> filteredData;
        filteredData = new FilteredList<>(membersList, b -> true);
        keywordTextFieldd.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(TeamMembers -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (String.valueOf(TeamMembers.getRiotId()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (TeamMembers.getMember_role().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(TeamMembers.getMemberPh()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (TeamMembers.getMember_mail().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(TeamMembers.getTeam_id()).indexOf(searchKeyword) > -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Team_mates> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvMembers.comparatorProperty());
        tvMembers.setItems(sortedData);

    }
   
}

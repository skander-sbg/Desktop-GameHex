/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import com.gamehex.entity.TeamMembers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.gamehex.entity.Teams;
import com.gamehex.service.GetPlayerByMail;
import com.gamehex.utils.MyConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import com.gamehex.entity.Session;
import com.gamehex.entity.User;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author Yasmine Daly
 */
public class TeamsFrontController implements Initializable {

    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField keywordTextField;
    @FXML
    private TextField tfTeamName;
    @FXML
    private TextField tfTeamTag;
    @FXML
    private TextField tfTeamReg;
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
    private TextField tfTeamId;
    private TableColumn<TeamMembers, Integer> colRiotId;
    private TableColumn<TeamMembers, String> colMemberRole;
    private TableColumn<TeamMembers, Integer> colMemberPh;
    private TableColumn<TeamMembers, String> colMemberMail;
    private TableColumn<TeamMembers, Integer> colTeamIdd;
    private TableView<TeamMembers> tvMembers;
    private TextField keywordTextFieldd;
    @FXML
    public TextField riot1;
    @FXML
    public JFXComboBox<String> role1;
    @FXML
    public JFXTextField memberPh1;
    @FXML
    public JFXTextField memberMail1;
    @FXML
    protected JFXTextField riot2;
    @FXML
    private JFXComboBox<String> role2;
    @FXML
    private JFXTextField memberPh2;
    @FXML
    private JFXTextField memberMail2;
    @FXML
    private JFXTextField riot3;
    @FXML
    private JFXComboBox<String> role3;
    @FXML
    private JFXTextField memberPh3;
    @FXML
    private JFXTextField memberMail3;
    @FXML
    private JFXTextField riot4;
    @FXML
    private JFXComboBox<String> role4;
    @FXML
    private JFXTextField memberPh4;
    @FXML
    private JFXTextField memberMail4;
    @FXML
    private JFXTextField riot5;
    @FXML
    private JFXComboBox<String> role5;
    @FXML
    private JFXTextField memberPh5;
    @FXML
    private JFXTextField memberMail5;
    @FXML
    private JFXTextField riot6;
    @FXML
    private JFXComboBox<String> role6;
    @FXML
    private JFXTextField memberPh6;
    @FXML
    private JFXTextField memberMail6;
    public static int id;
    @FXML
    private JFXButton btnWeb;
    public int x;
    public String y, z;
    public String mesg, code;
    public static String regionMap;

    GetPlayerByMail getPlayerByMail = new GetPlayerByMail();
    public static final String ACCOUNT_SID = "AC90ac083ebfd6f6485124fb25d08fbbb0";
    public static final String AUTH_TOKEN = "d9964c385389b51c5fe3ae2464a8b1b0";
    @FXML
    private JFXButton btnid;
    @FXML
    private JFXTextField malid;

    private Connection conn;
    User user = Session.StartSession().getSessionUser();
    int UID=0;
    @FXML
    private JFXTextField tfTeamMail;
    @FXML
    private Label lbRegion;
    @FXML
    private JFXButton btnMap;
    @FXML
    private JFXButton btnHome;

    public TeamsFrontController() {
        this.UID = user.getUserID();
        System.out.println("usrId:" +this.UID);
        conn = MyConnection.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //String s=role1.getSelectionModel().getSelectedItem().toString();
        ObservableList<String> list = FXCollections.observableArrayList("Captain", "Player", "Player", "Player", "Player", "Player");
        role1.setItems(list);
        role2.setItems(list);
        role3.setItems(list);
        role4.setItems(list);
        role5.setItems(list);
        role6.setItems(list);
        showTeams();
        // showMembers();
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Teams team = tvTeams.getSelectionModel().getSelectedItem();

        System.out.println("Id" + team.getTeamId());
        System.out.println("Name" + team.getTeamName());
        System.out.println("Tag" + team.getTeamTag());
        System.out.println("Mail" + team.getTeamMail());
        System.out.println("region" + team.getTeamReg());

        tfTeamId.setText("" + team.getTeamId());
        tfTeamName.setText("" + team.getTeamName());
        tfTeamTag.setText("" + team.getTeamTag());
        tfTeamMail.setText("" + team.getTeamMail());
        tfTeamReg.setText("" + team.getTeamReg());
        lbRegion.setText(team.getTeamReg());

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnInsert) {

            if (validateUsername(tfTeamName) & validateUsername(tfTeamTag) & validateUsername(tfTeamReg) & validateUsername(riot1) & validateUsername(riot2)
                    & validateUsername(riot3) & validateUsername(riot4) & validateUsername(riot5) & validateUsername(riot6)
                    & validateCombo(role1) & validateCombo(role2) & validateCombo(role3) & validateCombo(role4) & validateCombo(role5) & validateCombo(role6)
                    & validateEmail(tfTeamMail) & validateEmail(memberMail1) & validateEmail(memberMail2)
                    & validateEmail(memberMail3) & validateEmail(memberMail4) & validateEmail(memberMail5) & validateEmail(memberMail6)
                    & validatePhone(memberPh1) & validatePhone(memberPh2) & validatePhone(memberPh3)
                    & validatePhone(memberPh4) & validatePhone(memberPh5) & validatePhone(memberPh6)) {
                insertTeam();
            }
        
                
            } else if (event.getSource() == btnUpdate) {
                updateTeam();
            } else if (event.getSource() == btnDelete) {
                deleteTeam();

            }
        }
    

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

        String query = "SELECT * FROM teams WHERE userID = " + this.UID + "";
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

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    private void insertTeam() throws IOException {

        y = getSaltString();
        System.out.println(y + "dzedzdz");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+216" + memberPh1.getText()),
                new PhoneNumber("+19108308627"), y).create();
        System.out.println(y + "aaaaaaa");
        code = y;

    }

    private void insertTeam1() {
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamehex", "root", "");
        } catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
        try {
            String query = "insert into teams (teamName,teamTag,teamMail,teamReg, `userID`) values ('" + tfTeamName.getText() + "','" + tfTeamTag.getText() + "','" + tfTeamMail.getText() + "','" + tfTeamReg.getText() + "', '" + this.UID + "')";
            //executeQuery(query);

            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            
            String query1 = "INSERT INTO `teammembers` (`riotId`, `memberRole`, `memberPhone`, `memberMail`, `teamId`, `userID`) VALUES  ('" + riot1.getText() + "','" + role1.getSelectionModel().getSelectedItem() + "','" + memberPh1.getText() + "','" + memberMail1.getText() + "' , '" + generatedKey + "', '" + this.UID + "')";
            executeQuery(query1);
            String query2 = "INSERT INTO `teammembers` (`riotId`, `memberRole`, `memberPhone`, `memberMail`, `teamId`, `userID`) VALUES ('" + riot2.getText() + "','" + role2.getSelectionModel().getSelectedItem() + "','" + memberPh2.getText() + "','" + memberMail2.getText() + "','" + generatedKey + "', '" + this.UID + "')";
            executeQuery(query2);
            String query3 = "INSERT INTO `teammembers` (`riotId`, `memberRole`, `memberPhone`, `memberMail`, `teamId`, `userID`) VALUES ('" + riot3.getText() + "','" + role3.getSelectionModel().getSelectedItem() + "','" + memberPh3.getText() + "','" + memberMail3.getText() + "','" + generatedKey + "', '" + this.UID + "')";
            executeQuery(query3);
            String query4 = "INSERT INTO `teammembers` (`riotId`, `memberRole`, `memberPhone`, `memberMail`, `teamId`, `userID`) VALUES ('" + riot4.getText() + "','" + role4.getSelectionModel().getSelectedItem() + "','" + memberPh4.getText() + "','" + memberMail4.getText() + "','" + generatedKey + "', '" + this.UID + "')";
            executeQuery(query4);
            String query5 = "INSERT INTO `teammembers` (`riotId`, `memberRole`, `memberPhone`, `memberMail`, `teamId`, `userID`) VALUES ('" + riot5.getText() + "','" + role5.getSelectionModel().getSelectedItem() + "','" + memberPh5.getText() + "','" + memberMail5.getText() + "','" + generatedKey + "', '" + this.UID + "')";
            executeQuery(query5);
            String query6 = "INSERT INTO `teammembers` (`riotId`, `memberRole`, `memberPhone`, `memberMail`, `teamId`, `userID`) VALUES ('" + riot6.getText() + "','" + role6.getSelectionModel().getSelectedItem() + "','" + memberPh6.getText() + "','" + memberMail6.getText() + "','" + generatedKey + "', '" + this.UID + "')";
            executeQuery(query6);
           
            System.out.println("Inserted record's ID: " + generatedKey);
        } catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        }

        showTeams();
    }

    public TextField getMemberMail1() {
        return memberMail1;
    }

    private void updateTeam() {
        String query = "UPDATE teams SET teamName = '" + tfTeamName.getText() + "' , teamTag = '" + tfTeamTag.getText() + "' ,teamMail = '" + tfTeamMail.getText() + "' ,teamReg = '" + tfTeamReg.getText() + "' WHERE TeamId = " + tfTeamId.getText() + "";
        executeQuery(query);
        String query1 = "UPDATE teammembers SET riotId = '" + riot1.getText() + "' , memberRole = '" + role1.getSelectionModel().getSelectedItem() + "' ,memberPhone = '" + memberPh1.getText() + "' ,memberMail = '" + memberMail1.getText() + "' WHERE TeamId = " + tfTeamId.getText() + "";
        executeQuery(query1);
        String query2 = "UPDATE teammembers SET riotId = '" + riot2.getText() + "' , memberRole = '" + role2.getSelectionModel().getSelectedItem() + "' ,memberPhone = '" + memberPh2.getText() + "' ,memberMail = '" + memberMail2.getText() + "' WHERE TeamId = " + tfTeamId.getText() + "";
        executeQuery(query2);
        String query3 = "UPDATE teammembers SET riotId = '" + riot3.getText() + "' , memberRole = '" + role3.getSelectionModel().getSelectedItem() + "' ,memberPhone = '" + memberPh3.getText() + "' ,memberMail = '" + memberMail3.getText() + "' WHERE TeamId = " + tfTeamId.getText() + "";
        executeQuery(query3);
        String query4 = "UPDATE teammembers SET riotId = '" + riot4.getText() + "' , memberRole = '" + role4.getSelectionModel().getSelectedItem() + "' ,memberPhone = '" + memberPh4.getText() + "' ,memberMail = '" + memberMail4.getText() + "' WHERE TeamId = " + tfTeamId.getText() + "";
        executeQuery(query4);
        String query5 = "UPDATE teammembers SET riotId = '" + riot5.getText() + "' , memberRole = '" + role5.getSelectionModel().getSelectedItem() + "' ,memberPhone = '" + memberPh5.getText() + "' ,memberMail = '" + memberMail5.getText() + "' WHERE TeamId = " + tfTeamId.getText() + "";
        executeQuery(query5);
        String query6 = "UPDATE teammembers SET riotId = '" + riot6.getText() + "' , memberRole = '" + role6.getSelectionModel().getSelectedItem() + "' ,memberPhone = '" + memberPh6.getText() + "' ,memberMail = '" + memberMail6.getText() + "' WHERE TeamId = " + tfTeamId.getText() + "";
        executeQuery(query6);
        showTeams();
        //showMembers();
    }

    private void deleteTeam() {
        String query = "DELETE FROM teams WHERE teamId=" + tfTeamId.getText() + "";
        executeQuery(query);
        String query1 = "DELETE FROM teamMembers WHERE teamId=" + tfTeamId.getText() + "";
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
    private void Select(ActionEvent event) {
        String s1 = role1.getSelectionModel().getSelectedItem();
        String s2 = role2.getSelectionModel().getSelectedItem();
        String s3 = role3.getSelectionModel().getSelectedItem();
        String s4 = role4.getSelectionModel().getSelectedItem();
        String s5 = role5.getSelectionModel().getSelectedItem();
        String s6 = role6.getSelectionModel().getSelectedItem();
        ObservableList<String> list = FXCollections.observableArrayList("Player", "Player", "Player", "Player", "Player");
        ObservableList<String> list1 = FXCollections.observableArrayList("Captain", "Player", "Player", "Player", "Player", "Player");
        if ("Captain".equals(s1)) {
            role2.setItems(list);
            role3.setItems(list);
            role4.setItems(list);
            role5.setItems(list);
            role6.setItems(list);
        } else if ("Captain".equals(s2)) {
            role1.setItems(list);
            role3.setItems(list);
            role4.setItems(list);
            role5.setItems(list);
            role6.setItems(list);
        } else if ("Captain".equals(s3)) {
            role1.setItems(list);
            role2.setItems(list);
            role4.setItems(list);
            role5.setItems(list);
            role6.setItems(list);
        } else if ("Captain".equals(s3)) {
            role1.setItems(list);
            role4.setItems(list);
            role2.setItems(list);
            role5.setItems(list);
            role6.setItems(list);
        } else if ("Captain".equals(s4)) {
            role1.setItems(list);
            role3.setItems(list);
            role2.setItems(list);
            role5.setItems(list);
            role6.setItems(list);
        } else if ("Captain".equals(s5)) {
            role1.setItems(list);
            role3.setItems(list);
            role4.setItems(list);
            role2.setItems(list);
            role6.setItems(list);
        } else if ("Captain".equals(s6)) {
            role1.setItems(list);
            role3.setItems(list);
            role4.setItems(list);
            role5.setItems(list);
            role2.setItems(list);
        } else {
            role1.setItems(list1);
            role2.setItems(list1);
            role3.setItems(list1);
            role4.setItems(list1);
            role5.setItems(list1);
            role6.setItems(list1);
        }

    }

    @FXML
    private void handleButtonStat(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/gamehex/view/Scene.fxml"));
        //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
        Scene scene = new Scene(root);
        stage.setTitle("Statistics");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SendMail(ActionEvent event) throws IOException {

        if (malid.getText().equals(code)) {
            insertTeam1();

        } else {
            System.out.println("Code Invalide");

        }
    }

    /**
     * ************************************* Start Input Validation **************************************
     */
    private boolean validateUsername(TextField field) {
        Pattern p = Pattern.compile("[a-zA-Z0-9_]+");
        Matcher m = p.matcher(field.getText());
        if ((field.getText().length() != 0) && (m.find() && m.group().equals(field.getText()))) {
            field.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(field).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            field.setEffect(in);
            return false;
        }
    }

    private boolean validateEmail(TextField mail) {
        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(mail.getText());
        if ((mail.getText().length() != 0) && (m.find() && m.group().equals(mail.getText()))) {
            mail.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(mail).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            mail.setEffect(in);
            return false;
        }
    }

    private boolean validatePhone(TextField phone) {
        Pattern p;
        p = Pattern.compile("^([+]\\d{2})?\\s?(\\d{3}\\s?){2}$");
        Matcher m;
        m = p.matcher(phone.getText());
        if ((phone.getText().length() != 8)) {
            new animatefx.animation.Shake(phone).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            phone.setEffect(in);
            return false;
        } else {
            phone.setEffect(null);
            return true;
        }
    }

    private boolean validateCombo(JFXComboBox field) {

        if ((field.getSelectionModel().getSelectedItem() == null)) {
            new animatefx.animation.Shake(field).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            field.setEffect(in);
            return false;
        } else {
            field.setEffect(null);
            return true;
        }
    }
    
    
    /*************************************** End Input Validation ***************************************/

    @FXML
    private void OnRegionClicked(ActionEvent event) throws IOException {
        
        regionMap = lbRegion.getText();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/gamehex/view/WebView.fxml"));
        //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
        Scene scene = new Scene(root);
        stage.setTitle("Map");
        stage.setScene(scene);
        stage.show();
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

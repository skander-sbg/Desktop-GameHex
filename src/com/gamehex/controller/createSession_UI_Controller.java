/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import com.gamehex.entity.Session;
import com.gamehex.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.gamehex.entity.coachingSession;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.gamehex.utils.MyConnection;
import com.jfoenix.controls.JFXDatePicker;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Moudhaffer
 */
public class createSession_UI_Controller implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableColumn<coachingSession, SimpleIntegerProperty> col_id;
    @FXML
    private TableColumn<coachingSession, Date> col_Date;
    @FXML
    private TableColumn<coachingSession, Time> col_Start;
    @FXML
    private TableColumn<coachingSession, String> col_Coach;
    @FXML
    private TableColumn<coachingSession, String> col_Player;
    @FXML
    private Button btn_insert;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private JFXTextField keywordTextField;
    private final Connection cnx;
    @FXML
    private JFXComboBox cb_coach;
    @FXML
    private DatePicker dp_session;
    @FXML
    private TableView<coachingSession> tv_sessions;
    @FXML
    private JFXTimePicker tp_session;
    @FXML
    private Label label_warning;
    private JFXListView<String> coachListView;
    @FXML
    private JFXButton btn_back;
    @FXML
    private JFXButton btn_mySessions;
    @FXML
    private JFXButton btnHome;
    private double sessionRating;

    public TableColumn<coachingSession, SimpleIntegerProperty> getCol_id() {
        return col_id;
    }

    public TableColumn<coachingSession, Date> getCol_Date() {
        return col_Date;
    }

    public TableColumn<coachingSession, Time> getCol_Start() {
        return col_Start;
    }

    public TableColumn<coachingSession, String> getCol_Coach() {
        return col_Coach;
    }

    public TableColumn<coachingSession, String> getCol_Player() {
        return col_Player;
    }

    private final User user = Session.StartSession().getSessionUser();
    private int UID = 0;

    public createSession_UI_Controller(Connection cnx) {
        this.UID = user.getUserID();
        System.out.println("usrId:" + this.user.getUserID());
        this.cnx = cnx;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dp_session.setDayCellFactory(picker -> new DateCell() {
        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
        showCoaches();
        showSessions();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == btn_insert) {
            if (validateUsername() & validateDatePicker() & validateTimePicker()){
                System.out.println("Test Successful!");
                if (insertSession()) {
                    Stage stage = new Stage();
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/com/gamehex/view/rateMyApp.fxml"));
                        Scene scene = new Scene(root);
                        stage.setTitle("Rating");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(Coach_UIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
        if (event.getSource() == btn_update) {
            updateSession();

        }
        if (event.getSource() == btn_delete) {
            deleteSession();
        }
        if (event.getSource() == btn_mySessions) {
            showSessionsForCurrentUser();
        }
    }

    public createSession_UI_Controller() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public ObservableList<coachingSession> getSessionList() {
        ObservableList<coachingSession> sessionList = FXCollections.observableArrayList();
        String query = "SELECT * FROM session";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            coachingSession session;
            while (rs.next()) {
                session = new coachingSession(rs.getInt("sessionId"),
                        rs.getTime("startTime").toLocalTime(),
                        rs.getDate("Date").toLocalDate(),
                        rs.getString("coachId"),
                        rs.getString("userID"));
                sessionList.add(session);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sessionList;
    }

    public ObservableList<coachingSession> getSessionListForCurrentUser() {
        ObservableList<coachingSession> sessionList = FXCollections.observableArrayList();
        String query = "SELECT * FROM session where userID = " + this.user.getUserID();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            coachingSession session;
            while (rs.next()) {
                session = new coachingSession(rs.getInt("sessionId"),
                        rs.getTime("startTime").toLocalTime(),
                        rs.getDate("Date").toLocalDate(),
                        rs.getString("coachId"),
                        rs.getString("userID"));
                sessionList.add(session);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sessionList;
    }

    public void showCoaches() {
        String query = "select name from user where role = 'COACH';";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                cb_coach.getItems().addAll(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void showSessions() {
        ObservableList<coachingSession> list = getSessionList();

        //Setting ID field value
        getCol_id().setCellValueFactory(cellData -> new SimpleObjectProperty(Integer.toString(cellData.getValue().getSessionId())));
        //Setting Username field value
        getCol_Start().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getStartTime().toString()));
        //Setting Email field value
        getCol_Date().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getDate().toString()));

        getCol_Coach().setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoachAttendee()));
        //Setting type field
        getCol_Player().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getPlayerAttendee()));

        tv_sessions.setItems(list);

        FilteredList<coachingSession> filteredData = new FilteredList<>(list, b -> true);
        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(session -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (String.valueOf(session.getSessionId()).contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(session.getCoachAttendee()).contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(session.getPlayerAttendee()).contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(session.getDate()).contains(searchKeyword)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<coachingSession> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tv_sessions.comparatorProperty());
        tv_sessions.setItems(sortedData);
    }

    public void showSessionsForCurrentUser() {
        ObservableList<coachingSession> list = getSessionListForCurrentUser();

        //Setting ID field value
        getCol_id().setCellValueFactory(cellData -> new SimpleObjectProperty(Integer.toString(cellData.getValue().getSessionId())));
        //Setting Username field value
        getCol_Start().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getStartTime().toString()));
        //Setting Email field value
        getCol_Date().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getDate().toString()));

        getCol_Coach().setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoachAttendee()));
        //Setting type field
        getCol_Player().setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getPlayerAttendee()));

        tv_sessions.setItems(list);

        FilteredList<coachingSession> filteredData = new FilteredList<>(list, b -> true);
        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(session -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (String.valueOf(session.getSessionId()).contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(session.getCoachAttendee()).contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(session.getPlayerAttendee()).contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(session.getDate()).contains(searchKeyword)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<coachingSession> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tv_sessions.comparatorProperty());
        tv_sessions.setItems(sortedData);
    }

    private void executeQuery(String query) {
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean insertSession() {
        java.sql.Date sessionDate = java.sql.Date.valueOf(dp_session.getValue());
        String request = "select coachId from coach where userID = (select userID from user where name = '" + cb_coach.getValue().toString() + "')";
        Statement st;
        Integer coachId = 0;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                coachId = rs.getInt("coachId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(createSession_UI_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "INSERT INTO session(startTime, Date, coachId, userID, rating) values ('" + tp_session.getValue().toString() + "','" + dp_session.getValue().toString() + "', '" + coachId + "' , '" + this.user.getUserID() + "','" + 0 + "')";
        System.out.println(this.user.getUserID());
        try {
            Statement stt = cnx.createStatement();
            executeQuery(query);
            // stt.executeUpdate(query);
        } catch (SQLException ex) {
            label_warning.setVisible(true);
        }
        showSessionsForCurrentUser();
        return true;
    }

    private boolean updateSession() {
        String request = "select coachId from coach where userID = (select userID from user where name = '" + cb_coach.getValue().toString() + "'";
        Statement st;
        String name = "";
        try {
            st = cnx.createStatement();
            st.executeQuery(request);
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(createSession_UI_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "UPDATE session SET startTime = '" + tp_session.getValue().toString() + "' ,Date = '" + dp_session.getValue() + "' WHERE sessionId = " + handleMouseAction() + "";
        executeQuery(query);
        showSessions();
        return true;
    }

//    public void updateSession(){
//        String query = "UPDATE session SET coachAttendee= ?, playerAttendee= ?, startTime= ?, Date= ?,"
//                + " WHERE sessionId= ?;";
//        try {
//            PreparedStatement pst = cnx.prepareStatement(query);
//            pst.setString(1, txt_coach.getText());
//            pst.setString(2, txt_player.getText());
//            pst.setString(3, tp_session.getValue().toString());
//            pst.setString(4, dp_session.getValue().toString());
//            pst.setInt(5, Integer.parseInt(txt_sessionId.getText()));
//            pst.executeUpdate();
//        } catch (NumberFormatException | SQLException ex) {
//            ex.printStackTrace();
//        }
//        showSessions();
//    }
    private boolean validateUsername() {
        if (cb_coach.getSelectionModel().isEmpty()) {
            setStyle(cb_coach);
            return false;

        } else {
            cb_coach.setEffect(null);
            return true;
        }
    }

    private boolean validateDatePicker() {
        if (dp_session.getValue() == null) {
            setStyle(dp_session);
            return false;
        } else {
            dp_session.setEffect(null);
            return true;
        }
    }
    
private boolean validateTime() {
        Pattern p = Pattern.compile("^([1-9]|1[0-2])(a|p)([1-9]|1[0-2])(a|p)$");
        Matcher m = p.matcher(tp_session.getValue().toString());
        if ((tp_session.getValue().toString().length() != 0) && (m.find() && m.group().equals(tp_session.getValue().toString()))) {
            tp_session.setEffect(null);
            return true;
        } else {
            setStyle(tp_session);
            return false;
        }
    }

    private boolean validateTimePicker() {
        if (tp_session.getValue() == null & validateTime()) {
            setStyle(tp_session);
            return false;
        } else {

            tp_session.setEffect(null);
            return true;
        }
    }

    private void setStyle(DatePicker e) {
        new animatefx.animation.Shake(e).play();
        InnerShadow in = new InnerShadow();
        in.setColor(Color.web("#f80000"));
        e.setEffect(in);
    }

    private void setStyle(JFXTimePicker e) {
        new animatefx.animation.Shake(e).play();
        InnerShadow in = new InnerShadow();
        in.setColor(Color.web("#f80000"));
        e.setEffect(in);
    }

    private void setStyle(JFXComboBox e) {
        new animatefx.animation.Shake(e).play();
        InnerShadow in = new InnerShadow();
        in.setColor(Color.web("#f80000"));
        e.setEffect(in);
    }

    private void deleteSession() {
        String query = "DELETE FROM session WHERE sessionId=" + handleMouseAction() + "";
        executeQuery(query);
        showSessions();
        cb_coach.setValue(null);
        dp_session.setValue(null);
        tp_session.setValue(null);
    }

    @FXML
    private int handleMouseAction() {
        coachingSession session = tv_sessions.getSelectionModel().getSelectedItem();
        return session.getSessionId();
    }

    @FXML
    private void handleBackBtn(ActionEvent event) {
        if (event.getSource() == btn_back) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/com/gamehex/view/Home_Screen.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("Home Screen");
                stage.setScene(scene);
                stage.show();
                Stage stage2 = (Stage) btn_back.getScene().getWindow();
                stage2.close();
            } catch (IOException ex) {
                Logger.getLogger(getSummoner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
    
    public void getSessionRating(double sessionRating){
         this.sessionRating = sessionRating;
         System.out.println(this.sessionRating);
    }

}

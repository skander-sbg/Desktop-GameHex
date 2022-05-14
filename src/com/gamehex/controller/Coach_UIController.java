/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.gamehex.entity.Coaches;
import com.gamehex.entity.Session;
import com.gamehex.entity.User;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.gamehex.utils.MyConnection;

/**
 *
 * @passwd Moudhaffer
 */
public class Coach_UIController implements Initializable {

    @FXML
    private Button btn_insert;
    @FXML
    private Button btn_delete;

    //Singleton connection
    Connection cnx;
    @FXML
    private Label label;
    @FXML
    private JFXTextField txt_username;
    private JFXPasswordField txt_passwd;
    @FXML
    private JFXTextField keywordTextField;
    @FXML
    private Label label_warning;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXButton btn_back;
    @FXML
    private Label id_content;
    @FXML
    private Label username_content;
    @FXML
    private Label lastName_content;
    @FXML
    private Label type_content;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You Clicked me!");
        if (event.getSource() == btn_insert) {
            if (validateUsername() & validateEmail()) {
                if (insertCoach()) {
                    id_content.setVisible(true);
                    username_content.setVisible(true);
                    lastName_content.setVisible(true);
                    type_content.setVisible(true);
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
        if (event.getSource() == btn_delete) {
            deleteCoach();
        }
    }

    User user = Session.StartSession().getSessionUser();
    int UID = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_content.setVisible(false);
        username_content.setVisible(false);
        lastName_content.setVisible(false);
        type_content.setVisible(false);
        showCoaches();
    }

    //Getters & Setters
    public Connection getCnx() {
        return cnx;
    }

    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }

    public Coach_UIController() {
        this.UID = user.getUserID();
        System.out.println("usrId:" + this.user.getUserID());
        cnx = MyConnection.getInstance().getCnx();
    }

    public User getUserById(int id) {
        ObservableList<Coaches> coachList = FXCollections.observableArrayList();
        String query = "SELECT * FROM user where userID=" + id + "";

        User user = new User();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                user.setUserID(rs.getInt("userID"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setCIN(rs.getInt("CIN"));
                user.setPhone(rs.getInt("phone"));
                user.setDate(rs.getString("date"));
                user.setEmail(rs.getString("email"));
                user.setPwd(cryptWithMD5(rs.getString("pwd")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    private static MessageDigest md;

    public static String cryptWithMD5(String pass) {
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /*public ObservableList<Coaches> getCoachList() {
        ObservableList<Coaches> coachList = FXCollections.observableArrayList();
        String query = "SELECT * FROM coach";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            Coaches coach;
            while (rs.next()) {
                coach = new Coaches();
                coach.setCoachId(rs.getInt("coachId"));
                User us = getUserById(rs.getInt("userID"));
                coach.setUser(us);
                coach.setRating(rs.getInt("rating"));
                coachList.add(coach);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return coachList;
    }*/

    public void showCoaches() {

        //ObservableList<Coaches> list = getCoachList();

        //Setting ID field value
        id_content.setText("" + this.user.getUserID());
        id_content.setStyle("-fx-background-color:#273B56;");
        //Setting Username field value
        username_content.setText("" + this.user.getName());
        username_content.setStyle("-fx-background-color:#273B56;");
        //Setting Email field value
        lastName_content.setText("" + this.user.getLastName());
        lastName_content.setStyle("-fx-background-color:#273B56;");
        //Setting profileInfo field
        type_content.setText("" + this.user.getRole());
        type_content.setStyle("-fx-background-color:#273B56;");
    }

    public boolean insertCoach() {
        
        
        String query = "INSERT INTO coach(userID,rating) VALUES (?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, this.user.getUserID());
            pst.setInt(2,0);
            pst.executeUpdate();
            System.out.println("User Added sucessfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String request = "UPDATE user SET role = 'COACH' WHERE `userID` = ?";
        System.out.println(this.user.getUserID());
        try {
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1, this.user.getUserID());
            pst.executeUpdate();
            System.out.println("User Added sucessfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        showCoaches();
        return true;
    }
    
    public boolean giveRating(){
        
        String request = "select AVG(rating) from session where coachId ="
                + " (select coachId from coach where userID = ?);";
        Coaches coach = new Coaches();
        try {
            PreparedStatement psst = cnx.prepareCall(request);
            psst.setInt(1, this.user.getUserID());
            ResultSet rs = psst.executeQuery(request);
            while(rs.next()){
                coach.setRating(rs.getFloat("rating"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Coach_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String query = "UODATE coach SET rating = ? WHERE coachId = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setFloat(1, coach.getRating());
            pst.setInt(2, UID);
            pst.executeUpdate();
            System.out.println("User Added sucessfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        showCoaches();
        return true;
    }

    private void setStyle(JFXTextField e) {
        new animatefx.animation.Shake(e).play();
        InnerShadow in = new InnerShadow();
        in.setColor(Color.web("#f80000"));
        e.setEffect(in);
    }

    private boolean validateUsername() {
        Pattern p = Pattern.compile("[a-zA-Z0-9_]+");
        Matcher m = p.matcher(txt_username.getText());
        if ((txt_username.getText().length() != 0) && (m.find() && m.group().equals(txt_username.getText()))) {
            txt_username.setEffect(null);
            return true;
        } else {
            setStyle(txt_username);
            return false;
        }
    }

    private boolean validatePassword() {
        Pattern p = Pattern.compile("[a-zA-Z0-9_]+");
        Matcher m = p.matcher(txt_passwd.getText());
        if ((txt_passwd.getText().length() != 0)
                && (m.find() && m.group().equals(txt_passwd.getText()))) {
            txt_passwd.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(txt_passwd).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            txt_passwd.setEffect(in);
            return false;
        }
    }

    private boolean validateEmail() {
        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(txt_email.getText());
        if ((txt_email.getText().length() != 0) && (m.find()
                && m.group().equals(txt_email.getText()))
                    && (txt_email.getText().equals(user.getEmail()))) {
            txt_email.setEffect(null);
            return true;
        } else {
            setStyle(txt_email);
            return false;
        }
    }

    public void deleteCoach() {
        String query = "DELETE FROM coach WHERE coachId=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, this.user.getUserID());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        showCoaches();
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;


import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.gamehex.entity.Matches;
import com.gamehex.utils.MyConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import java.sql.SQLException;
import javafx.scene.Parent;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Yasmine Daly
 */
public class MatchesBackController implements Initializable {

    @FXML
    private TextField tfMatchId;
    @FXML
    private JFXComboBox<Integer> tfTeam1;
    @FXML
    private JFXComboBox<Integer> tfTeam2;
    @FXML
    private TextField tfMatchCom;
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
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField keywordTextField;
    @FXML
    private Button btnNotepad;
    @FXML
    private JFXDatePicker tfMatchDate;
    @FXML
    private JFXTimePicker tfMatchTime;
    @FXML
    private TableColumn<Matches, Date> colMatchDate;
    @FXML
    private TableColumn<Matches, Time> colMatchTime;
    @FXML
    private JFXComboBox<String> comMatchRes;
    @FXML
    private JFXButton btnSendMail;
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

    public TableColumn<Matches,String> getColMatchCom() {
        return colMatchCom;
    }

    public TableColumn<Matches, Date> getColMatchDate() {
        return colMatchDate;
    }

    public TableColumn<Matches, Time> getColMatchTime() {
        return colMatchTime;
    }
    

    private Connection conn;

    public MatchesBackController() {
        conn=MyConnection.getInstance().getCnx();
    }

   
    
    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnInsert) {
            if(validateFields(tfMatchCom) & validateCombo(tfTeam1) & validateCombo(tfTeam2) & validateCombo(comMatchRes) ){
              insertMatch();  
            }
            
        } else if (event.getSource() == btnUpdate) {
            updateMatch();
        } else if (event.getSource() == btnDelete) {
            deleteMatch();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> list = FXCollections.observableArrayList("TEAM1_WINS","TEAM2_WINS","MATCH_NULL");
        comMatchRes.setItems(list);
        
        
        String query = "SELECT id FROM teams";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query); 
           
            while(rs.next()){
                tfTeam1.getItems().addAll(rs.getInt("id"));
                tfTeam2.getItems().addAll(rs.getInt("id"));
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        
    }
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
                }else if (String.valueOf(Matches.getMatch_time()).indexOf(searchKeyword) > -1) {
                    return true;
                }else {
                    return false;
                }
            });
        });
        SortedList<Matches> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvMatches.comparatorProperty());
        tvMatches.setItems(sortedData);

    }

    private void insertMatch() {
        
        
        
        String query = "insert into matches (team1_id,team2_id,match_res,match_com,match_date, match_time) values ('" + tfTeam1.getSelectionModel().getSelectedItem().toString() + "','" + tfTeam2.getSelectionModel().getSelectedItem().toString() + "','" + comMatchRes.getSelectionModel().getSelectedItem().toString() + "','" + tfMatchCom.getText() + "','" + tfMatchDate.getValue().toString() +"' , '" +tfMatchTime.getValue().toString()+ "')";
        executeQuery(query);
        showMatches();
    }

    private void updateMatch() {
        String query = "UPDATE matches SET team1_id = '" + tfTeam1.getSelectionModel().getSelectedItem().toString() + "' , team2_id = '" + tfTeam2.getSelectionModel().getSelectedItem().toString() + "' ,match_res = '" + comMatchRes.getSelectionModel().getSelectedItem().toString() + "' ,match_com = '" + tfMatchCom.getText() + "' ,match_date = '" + tfMatchDate.getValue().toString() + "' ,match_time = '" + tfMatchTime.getValue().toString()+ "' WHERE id = " + tfMatchId.getText() + "";
        executeQuery(query);
        showMatches();
    }

    private void deleteMatch() {
        String query = "DELETE FROM matches WHERE id=" + tfMatchId.getText() + "";
        executeQuery(query);
        showMatches();
    }

    private void executeQuery(String query) {
        
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Matches match = tvMatches.getSelectionModel().getSelectedItem();
        System.out.println("Id" + match.getId());
        System.out.println("team1" + match.getTeam1_id());
        System.out.println("team2" + match.getTeam2_id());
        System.out.println("Result" + match.getMatch_res());
        System.out.println("Comment" + match.getMatch_com());
        System.out.println("Date" + match.getMatch_date());
        System.out.println("Time" + match.getMatch_time());

        tfMatchId.setText("" + match.getId());
        tfTeam1.setValue(match.getTeam1_id());
        tfTeam2.setValue(match.getTeam2_id());
        comMatchRes.setValue("" + match.getMatch_res());
        tfMatchCom.setText("" + match.getMatch_com());
        tfMatchDate.setValue(match.getMatch_date());
        tfMatchTime.setValue(match.getMatch_time());
    }

    @FXML
    private void onNotepadClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/gamehex/view/NotePad.fxml"));
        stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
        Scene scene = new Scene(root);
        stage.setTitle("Notepad");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onSendMailClick(ActionEvent event) {
         try {
            
            
            Stage stage = new Stage();
            Parent etab = FXMLLoader.load(getClass().getResource("/com/gamehex/view/sendMail.fxml"));
            //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
            Scene scene = new Scene(etab);
            stage.setTitle("Matches");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private LocalDate getDate(ActionEvent event) {
        LocalDate myDate = tfMatchDate.getValue();
        return myDate;
    }

    @FXML
    private LocalTime getTime(ActionEvent event) {
        LocalTime myTime = tfMatchTime.getValue();
        return myTime;
    }


    public void setTfMatchDate(JFXDatePicker tfMatchDate) {
        this.tfMatchDate = tfMatchDate;
    }

    public void setTfMatchTime(JFXTimePicker tfMatchTime) {
        this.tfMatchTime = tfMatchTime;
    }
    /*************************************** Start Input Validation ***************************************/
    
    

 

    private boolean validateFields(TextField field) {
        
        
        
        if ((field.getText().length() == 0) ) {
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
    
    private boolean validateCombo(JFXComboBox field) {
        
        
        
        if ((field.getSelectionModel().getSelectedItem() == null) ) {
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
 

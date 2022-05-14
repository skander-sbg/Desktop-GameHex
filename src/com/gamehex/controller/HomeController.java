/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yasmine Daly
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void MatchesClicked(MouseEvent event) {
        try {

            Stage stage = new Stage();
            Parent etab = FXMLLoader.load(getClass().getResource("/com/gamehex/view/Matches.fxml"));
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
    private void TeamsClicked(MouseEvent event) {
        try {

            Stage stage = new Stage();
            Parent etab = FXMLLoader.load(getClass().getResource("/com/gamehex/view/Teams.fxml"));
            //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
            Scene scene = new Scene(etab);
            stage.setTitle("Teams");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void OnTournamentsClickedfront(MouseEvent event) {
        try {

            

            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/TournamentChooseFront.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Tournaments");
            //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
        }

    }

    @FXML
    private void OnTournamentsClicked(MouseEvent event) {
    }

    @FXML
    private void OnCoachingClickedfront(MouseEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/Home_Screen.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Coaching");
            //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void onArticlesClickedfront(MouseEvent event) throws IOException {
        
          FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/ArticleFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Coaching");
            //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void OnUserClickedfront(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/UserFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Coaching");
            //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

}

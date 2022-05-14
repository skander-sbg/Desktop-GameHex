/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.view;

import javafx.stage.Stage;
import java.net.URL;
import javafx.animation.FadeTransition;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Yasmine Daly
 */
public class ViewText {
    
    
    private final Stage stage;

    private final FadeTransition fadeIn;
    private final FadeTransition fadeOut;
    private final ImageView imageView;

    public ViewText(Stage stage) {

        this.stage = stage;
        this.imageView = setImage();
        Group root = new Group(imageView);
        fadeIn = new FadeTransition(Duration.seconds(5), root);
        fadeOut = new FadeTransition(Duration.seconds(3), root);        
        initConf();
        Scene scene = new Scene(root, 650, 650);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
    }

    
    private void initConf(){
        initImage();
        confFade();
        startFadeIn();
    }
    
    private ImageView setImage() {
        URL imageURL = getClass().getResource("home_page.png");
        return new ImageView(new Image(imageURL.toExternalForm()));
    }

    private void initImage() {
        imageView.setX(50);
        imageView.setY(25);
        imageView.setFitHeight(600);
        imageView.setFitWidth(600);
        imageView.setPreserveRatio(true);
    }


    private void confFade() {
        fadeIn();
        fadOut();
        fadeInFinished();
        fadeOutFinished();
    }

    private void startFadeIn() {
        fadeIn.play();
    }

    private void fadeIn() {
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
    }

    private void fadOut() {
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);
    }

    private void fadeInFinished() {
        fadeIn.setOnFinished((e) -> {
            fadeOut.play();
        });

    }

    private void fadeOutFinished() {
        fadeOut.setOnFinished((e) -> {
            stage.close();
        });

    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




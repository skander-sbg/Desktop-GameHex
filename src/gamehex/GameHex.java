/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamehex;

import javafx.application.Application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.gamehex.mediator.Mediator;

/**
 *
 * @author Yasmine Daly
 */
public class GameHex extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Mediator.getInstance().managerView(primaryStage);
        Stage stage= new Stage();
        Parent root;
            root = FXMLLoader.load(getClass().getResource("/com/gamehex/view/loginFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("GameHex");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
   

}

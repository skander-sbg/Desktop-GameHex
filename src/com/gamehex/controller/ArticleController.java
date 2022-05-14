/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.gamehex.entity.Article;
import com.gamehex.entity.Session;
import com.gamehex.entity.User;
import com.gamehex.service.ArticlesService;



/**
 *
 * @author asus
 */
public class ArticleController implements Initializable{

    @FXML
    private TextArea tfArticle;
    @FXML
    private TableView<Article> tvArticles;
    @FXML
    private TableColumn<Article, Integer> tvID;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TableColumn<Article, String> tvContent;
    
    @FXML
    private Button signout;
    
    @FXML
    private Label labelName;
    @FXML
    private Button profile;
    
    
    
    private void dispayutils(ObservableList<Article> listArticle) {
        tvID.setCellValueFactory(new PropertyValueFactory<Article, Integer>("articleID"));
        tvContent.setCellValueFactory(new PropertyValueFactory<Article , String>("content"));
        
        tvArticles.setItems(listArticle);
    }
    
    public void showArticle(){
        ArticlesService as = new ArticlesService();
        ObservableList<Article> listArticle = as.displayArticle();
        dispayutils(listArticle);
        System.out.println(listArticle);
    }
    
    //********* session **********
    User u=Session.StartSession().getSessionUser();
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
         //UsersService us = new UsersService();
        labelName.setText("WELCOME   "+u.getName()+" "+u.getLastName());
        
        
        showArticle();
        
        add.setOnAction(event->{
            if((tfArticle.getText().isEmpty())){
               
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Article can not be created !!! ");
               alert.setContentText("Please fill in the fild !!! ");
               alert.show(); 
            }
            else{
            Article a = new Article( tfArticle.getText());
            ArticlesService as = new ArticlesService();
            as.addArticle(a);
            tfArticle.setText(""); 
            showArticle();
            }
        });
        
        delete.setOnAction(event->{
            Article a = (Article)tvArticles.getSelectionModel().getSelectedItem();
            ArticlesService as = new ArticlesService();
            as.deleteArticle(a.getArticleID());
            tfArticle.setText("");
            showArticle();
        });
        
        update.setOnAction(event->{
            Article a = (Article)tvArticles.getSelectionModel().getSelectedItem();
            if((tfArticle.getText().isEmpty())){
               
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Article can not be updated !!! ");
               alert.setContentText("Please fill in the fild !!! ");
               alert.show(); 
            }
            else{
            ArticlesService as = new ArticlesService();
            Article a2 = new Article(a.getArticleID(),tfArticle.getText());
            as.updateArticle(a2);
            tfArticle.setText("");
            showArticle();
            }
        });
        
        signout.setOnAction(event->{
            try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/loginFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying the interface");
            }  
        });
        
        profile.setOnAction(event->{
            try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/profileFXML.fxml"));
            //UserController UC = loader.getController();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying the interface");
            }  
        });
        
    }

    int index=-1;
    @FXML
    private void forUpdate(MouseEvent event) {
        index =tvArticles.getSelectionModel().getSelectedIndex();
   if(index<= -1){
    return ;
    }
   tfArticle.setText(tvContent.getCellData(index));
   
    }
    
    
}

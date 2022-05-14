/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.gamehex.utils.MyConnection;
import com.gamehex.entity.Article;



/**
 *
 * @author asus
 */
public class ArticlesService {
    
    Connection cnx2;
    public ArticlesService(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    public void deleteArticle(int ID){
            String request4 = "DELETE FROM articles WHERE articleID ='" + ID + "'";
            System.out.println(request4);
            try {
                 PreparedStatement pst = cnx2.prepareStatement(request4);
                 // pst.setInt(1,  u.getUserID());
                 pst.executeUpdate();
                } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                } 
    }
    
    public void addArticle(Article a){
        try {
            String request2 = "INSERT INTO articles (content)"
                              + "VALUES (?)";
            PreparedStatement pst = cnx2.prepareStatement(request2);
            pst.setString(1, a.getContent());
            pst.executeUpdate();
            System.out.println("Your article has been added"); 
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            }
    }
    
    public void updateArticle(Article a){
        try {
             String request5 = "UPDATE articles SET content =? WHERE articleID = ?";
             System.out.println(request5);
             PreparedStatement pst = cnx2.prepareStatement(request5);
             pst.setString(1, a.getContent());
             pst.setInt(2, a.getArticleID());
             pst.executeUpdate();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage());
             //ex.printStackTrace();
             }
    }
    
    public ObservableList<Article> displayArticle(){
        ObservableList<Article> myList = FXCollections.observableArrayList();
        try {
             String request3 = "SELECT * FROM articles";
             Statement st = cnx2.createStatement();
             ResultSet rs = st.executeQuery(request3);
             while(rs.next()){
                              Article a = new Article();
                              a.setArticleID(rs.getInt("articleID"));
                              a.setContent(rs.getString("content"));
                              myList.add(a);
                              }
            
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            }
          return myList;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import com.gamehex.utils.MyConnection;
import com.gamehex.entity.User;



/**
 *
 * @author asus
 */
public class UsersService {
    
    Connection cnx2;
    public UsersService(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    
    public void deleteUser(int ID){
        String request4 = "DELETE FROM user WHERE userID ='" + ID + "'";
        System.out.println(request4);
        try {
             PreparedStatement pst = cnx2.prepareStatement(request4);
             // pst.setInt(1,  u.getUserID());
             pst.executeUpdate();
             } catch (SQLException ex) {
             System.err.println(ex.getMessage());
             }  
    }
    
    
    public void addUser(User u){
        try {
            String request2 = "INSERT INTO user (name,lastName,CIN,phone,date,email,pwd)"
                              + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(request2);
            pst.setString(1, u.getName());
            pst.setString(2, u.getLastName());
            pst.setInt(3, u.getCIN());
            pst.setInt(4, u.getPhone());
            pst.setString(5, u.getDate());
            pst.setString(6, u.getEmail());
            pst.setString(7, cryptWithMD5(u.getPwd()));
            pst.executeUpdate();
            System.out.println("Your user has been added");
            
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            }
    }
    
    
     public void updateUser(User u){
        try {
            String request5 = "UPDATE user SET name =?,lastName=?,CIN=?,phone=?,date=?,email=?,pwd=? WHERE userID = ?";
            System.out.println(request5);
            PreparedStatement pst = cnx2.prepareStatement(request5);
            pst.setString(1, u.getName());
            pst.setString(2, u.getLastName());
            pst.setInt(3, u.getCIN());
            pst.setInt(4, u.getPhone());
            pst.setString(5, u.getDate());
            pst.setString(6, u.getEmail());
            pst.setString(7, cryptWithMD5(u.getPwd()));
            pst.setInt(8, u.getUserID());
            pst.executeUpdate();
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            //ex.printStackTrace();
            }
    }
    
     /*public ResultSet displayUserOnly(){
  
        try {
            String request = "SELECT name FROM user";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(request);
            
                //User u = new User();
                //u.setName(rs.getString("name"));
                //u.setLastName(rs.getString("lastName"));
                 
                
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
     }*/
    
     public ObservableList<User> displayUser(){
        ObservableList<User> myList = FXCollections.observableArrayList();
        try {
            String request3 = "SELECT * FROM user";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(request3);
            while(rs.next()){
                User u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setName(rs.getString("name"));
                u.setLastName(rs.getString("lastName"));
                u.setCIN(rs.getInt("CIN"));
                u.setPhone(rs.getInt("phone"));
                u.setDate(rs.getString("date"));
                u.setEmail(rs.getString("email"));
                u.setPwd(cryptWithMD5(rs.getString("pwd")));
                myList.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
        /*public ObservableList<User> displayUserSort(){
        
        ObservableList<User> myList = FXCollections.observableArrayList();
        try {
            String request3 = "SELECT * FROM user";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(request3);
            while(rs.next()){
                User u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setName(rs.getString("name"));
                u.setLastName(rs.getString("lastName"));
                u.setCIN(rs.getInt("CIN"));
                u.setPhone(rs.getInt("phone"));
                u.setDate(rs.getString("date"));
                u.setEmail(rs.getString("email"));
                u.setPwd(cryptWithMD5(rs.getString("pwd")));
                myList.add(u);
            }
            myList.sort((User u1,User u2)->{return u1.getName().equals(u2.getName());});
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }*/
     public ObservableList<User> sortUP(){
        ObservableList<User> myList = FXCollections.observableArrayList();
             try {
             String request3 = "SELECT * FROM user" + " ORDER BY name ASC";
             Statement st = cnx2.createStatement();
             ResultSet rs = st.executeQuery(request3);
             while(rs.next()){
                User u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setName(rs.getString("name"));
                u.setLastName(rs.getString("lastName"));
                u.setCIN(rs.getInt("CIN"));
                u.setPhone(rs.getInt("phone"));
                u.setDate(rs.getString("date"));
                u.setEmail(rs.getString("email"));
                u.setPwd(cryptWithMD5(rs.getString("pwd")));
                myList.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;     
     }
     
     public ObservableList<User> sortDOWN(){
             ObservableList<User> myList = FXCollections.observableArrayList();
             try {
                  String request3 = "SELECT * FROM user" + " ORDER BY name DESC";
                  Statement st = cnx2.createStatement();
                  ResultSet rs = st.executeQuery(request3);
                  while(rs.next()){
                           User u = new User();
                           u.setUserID(rs.getInt("userID"));
                           u.setName(rs.getString("name"));
                           u.setLastName(rs.getString("lastName"));
                           u.setCIN(rs.getInt("CIN"));
                           u.setPhone(rs.getInt("phone"));
                           u.setDate(rs.getString("date"));
                           u.setEmail(rs.getString("email"));
                           u.setPwd(cryptWithMD5(rs.getString("pwd")));
                           myList.add(u);
                    }
            
                 } catch (SQLException ex) {
                 System.err.println(ex.getMessage());
                 }
           return myList;     
     }
     
     
     public ObservableList searchByNameCIN(String n){
        ObservableList<User> list=FXCollections.observableArrayList();
        try {
            String request = "SELECT * FROM user WHERE (name like ?)or(CIN like ?) ";
            System.out.println(request);
            PreparedStatement pst=cnx2.prepareStatement(request);
            pst.setString(1, n+"%");
            pst.setString(2, n+"%");
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                User u =new User();
                u.setUserID(rs.getInt(1));
                //u.setName(CategoryDao.getInstance().displayById(rs.getInt(2)));
                u.setName(rs.getString(2));
                u.setLastName(rs.getString(3));
                u.setCIN(rs.getInt(4));
                u.setPhone(rs.getInt(5));
                u.setDate(rs.getString(6));
                u.setEmail(rs.getString(7));
                u.setPwd(cryptWithMD5(rs.getString(8)));
                list.add(u);
              }
            
             } catch (SQLException ex) {
             System.err.println(ex.getMessage());
             }
        return list;
    }
    
     
      private static MessageDigest md;
      public static String cryptWithMD5(String pass){
         try {
              md = MessageDigest.getInstance("MD5");
              byte[] passBytes = pass.getBytes();
              md.reset();
              byte[] digested = md.digest(passBytes);
              StringBuffer sb = new StringBuffer();
              for(int i=0;i<digested.length;i++){
              sb.append(Integer.toHexString(0xff & digested[i]));
              }
         return sb.toString();
              } catch (NoSuchAlgorithmException ex) {
               Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
              }
         return null;
   }
     
     
     public boolean checklogin(String email,String pwd){
        try {
            Statement st = cnx2.createStatement();
            String request="SELECT * FROM user WHERE email='"+email+"' AND `pwd`='"+cryptWithMD5(pwd)+"'";
            ResultSet rs=st.executeQuery(request);
            return rs.next();
            } catch (SQLException ex) {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
     }
     
     
     public User existByEmail(String tfCheck){
         try{
            Statement st = cnx2.createStatement();
            String request="SELECT * FROM user WHERE email='"+tfCheck+"'";
            ResultSet rs=st.executeQuery(request);
            while (rs.next()){
                User u = new User();
                u.setUserID(rs.getInt("userID"));
                u.setName(rs.getString("name"));
                u.setLastName(rs.getString("lastName"));
                u.setCIN(rs.getInt("CIN"));
                u.setPhone(rs.getInt("phone"));
                u.setDate(rs.getString("date"));
                u.setEmail(rs.getString("email"));
                u.setPwd(cryptWithMD5(rs.getString("pwd")));
            return u;
                    }
             }catch(Exception e){
             System.out.println("not found");}
             return null;
             }
     
     
     public void changePWD(User u){
        try {
            String request5 = "UPDATE user SET pwd=? WHERE email = ?";
            System.out.println(request5);
            PreparedStatement pst = cnx2.prepareStatement(request5);
            pst.setString(1, cryptWithMD5(u.getPwd()));
            pst.setString(2, u.getEmail());
            pst.executeUpdate();
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            //ex.printStackTrace();
            }
 
     }
     
     public User findName(String tfCheck){
         try{
            Statement st = cnx2.createStatement();
            String request="SELECT name,lastName,role FROM user WHERE email='"+tfCheck+"'";
            ResultSet rs=st.executeQuery(request);
            while (rs.next()){
                User u = new User();
                u.setName(rs.getString("name"));
                u.setLastName(rs.getString("lastName"));
                u.setRole(rs.getString("role"));
            return u;
                    }
             }catch(Exception e){
             System.out.println("not found");}
             return null;
             }

     
}
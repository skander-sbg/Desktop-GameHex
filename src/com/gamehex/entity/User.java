/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.entity;

/**
 *
 * @author asus
 */
public class User {
    private int userID;
    private String name;
    private String lastName;
    private int CIN;
    private int phone;
    private String date;
    private String email;
    private String pwd;
    private String role;

    public User(){
        
    }

    public User(int userID, String name, String lastName, int CIN, int phone, String date, String email, String pwd, String role) {
        this.userID = userID;
        this.name = name;
        this.lastName = lastName;
        this.CIN = CIN;
        this.phone = phone;
        this.date = date;
        this.email = email;
        this.pwd = pwd;
        this.role = role;
    }
    
    public User(int userID, String name, String lastName, int CIN, int phone, String date, String email, String pwd) {
        this.userID = userID;
        this.name = name;
        this.lastName = lastName;
        this.CIN = CIN;
        this.phone = phone;
        this.date = date;
        this.email = email;
        this.pwd = pwd;
    }

    public User(String name, String lastName, int CIN, int phone, String date, String email, String pwd) {
        this.name = name;
        this.lastName = lastName;
        this.CIN = CIN;
        this.phone = phone;
        this.date = date;
        this.email = email;
        this.pwd = pwd;
    }

    public User(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", name=" + name + ", lastName=" + lastName + ", CIN=" + CIN + ", phone=" + phone + ", date=" + date + ", email=" + email + ", pwd=" + pwd + ", role=" + role + '}';
    }

    
    
    
    
    
}

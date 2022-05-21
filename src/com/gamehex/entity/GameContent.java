/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.entity;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class GameContent {
    //ATTRIBUTES - GETTERS - SETTERS - TOSTRING - CONSTRUCTORS
    
    private int id_GameContent;
    private String title;
    private String videoURL;
    private Date uploadDate;

    
    public GameContent (){};
    
    public GameContent(int id_GameContent, String title, String videoURL, Date uploadDate){
        this.id_GameContent = id_GameContent;
        this.title = title;
        this.videoURL = videoURL;
        this.uploadDate = uploadDate ;
    }
    
    public GameContent(int id_GameContent, String title, String videoURL){
        this.id_GameContent = id_GameContent;
        this.title = title;
        this.videoURL = videoURL;
    }
    
    public GameContent(int id_GameContent, String title){
        this.id_GameContent = id_GameContent;
        this.title = title;
    }
    
    public GameContent(String title){
        this.title = title;
    }

    public int getId_GameContent() {
        return id_GameContent;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoURL() {
        return videoURL;
    }
    
    public Date getUploadDate() {
    return uploadDate;
    }
    

    public void setId_GameContent(int id_GameContent) {
        this.id_GameContent = id_GameContent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
    
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "GameContent{" + "id_GameContent=" + id_GameContent + ", Title=" + title + ", URL=" + videoURL + ", Date =" + uploadDate + '}';
    }

}

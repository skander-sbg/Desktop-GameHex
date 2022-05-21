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
public class Tuto {
        //ATTRIBUTES - GETTERS - SETTERS - TOSTRING - CONSTRUCTORS
    
    private int id_Tuto;
    private String title;
    private String Tuto;
    private Date uploadDate;

    public Tuto() {
    }

    
    
    public Tuto(int id_Tuto, String title, String Tuto, Date uploadDate) {
        this.id_Tuto = id_Tuto;
        this.title = title;
        this.Tuto = Tuto;
        this.uploadDate = uploadDate;
    }

    public Tuto(String title, String Tuto, Date uploadDate) {
        this.title = title;
        this.Tuto = Tuto;
        this.uploadDate = uploadDate;
    }

    public Tuto(int id_Tuto, String Tuto) {
        this.id_Tuto = id_Tuto;
        this.Tuto = Tuto;
    }

    public Tuto(String Tuto, Date uploadDate) {
        this.Tuto = Tuto;
        this.uploadDate = uploadDate;
    }

    public Tuto(int id_Tuto, String Tuto, Date uploadDate) {
        this.id_Tuto = id_Tuto;
        this.Tuto = Tuto;
        this.uploadDate = uploadDate;
    }

    
    
    public Tuto(String title, String Tuto) {
        this.title = title;
        this.Tuto = Tuto;
    }

    public Tuto(String Tuto) {
        this.Tuto = Tuto;
    }

    public int getId_Tuto() {
        return id_Tuto;
    }

    public String getTitle() {
        return title;
    }

    public String getTuto() {
        return Tuto;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setId_Tuto(int id_Tuto) {
        this.id_Tuto = id_Tuto;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTuto(String Tuto) {
        this.Tuto = Tuto;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "Tuto{" + "id_Tuto=" + id_Tuto + ", title=" + title + ", Tuto=" + Tuto + ", uploadDate=" + uploadDate + '}';
    }
    
    
    
    
}

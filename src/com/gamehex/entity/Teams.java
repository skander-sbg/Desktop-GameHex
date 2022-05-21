/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.entity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Yasmine Daly
 */
public class Teams {
    private int  id;
    private String team_logo;
    private String team_name;
    private String team_tag;
    private String team_reg;
    private String team_mail;
    

    public Teams(int teamId, String teamName, String teamTag, String teamMail, String teamRegion) {
        this.id = teamId;
        //this.team_logo = team_logo;
        this.team_name = teamName;
        this.team_tag = teamTag;
        this.team_reg = teamRegion;
        this.team_mail = teamMail;
       
    }

    public Integer getTeamId() {
        return id;
    }

    public String getTeam_logo() {
        return team_logo;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getTeam_tag() {
        return team_tag;
    }

    public String getTeamReg() {
        return team_reg;
    }

    public String getTeam_mail() {
        return team_mail;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setTeam_logo(String team_logo) {
        this.team_logo = team_logo;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public void setTeam_tag(String team_tag) {
        this.team_tag = team_tag;
    }

    public void setTeamReg(String teamRegion) {
        this.team_reg = teamRegion;
    }

    public void setTeam_mail(String team_mail) {
        this.team_mail = team_mail;
    }

  

    @Override
    public String toString() {
        return "Teams{" + "teamId=" + id + ", teamLogo=" + team_logo + ", teamName=" + team_name + ", teamTag=" + team_tag + ", teamRegion=" + team_reg + ", teamMail=" + team_mail +  '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.team_logo);
        hash = 17 * hash + Objects.hashCode(this.team_name);
        hash = 17 * hash + Objects.hashCode(this.team_tag);
        hash = 17 * hash + Objects.hashCode(this.team_reg);
        hash = 17 * hash + Objects.hashCode(this.team_mail);
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Teams other = (Teams) obj;
        if (!Objects.equals(this.team_logo, other.team_logo)) {
            return false;
        }
        if (!Objects.equals(this.team_name, other.team_name)) {
            return false;
        }
        if (!Objects.equals(this.team_tag, other.team_tag)) {
            return false;
        }
        if (!Objects.equals(this.team_reg, other.team_reg)) {
            return false;
        }
        if (!Objects.equals(this.team_mail, other.team_mail)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
       
        return true;
    }

   

   
    }

   
    
    


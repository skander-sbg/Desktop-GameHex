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
    private int  teamId;
    private String teamLogo;
    private String teamName;
    private String teamTag;
    private String teamRegion;
    private String teamMail;
    

    public Teams(int teamId, String teamName, String teamTag, String teamMail, String teamRegion) {
        this.teamId = teamId;
        //this.teamLogo = teamLogo;
        this.teamName = teamName;
        this.teamTag = teamTag;
        this.teamRegion = teamRegion;
        this.teamMail = teamMail;
       
    }

    public Integer getTeamId() {
        return teamId;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamTag() {
        return teamTag;
    }

    public String getTeamReg() {
        return teamRegion;
    }

    public String getTeamMail() {
        return teamMail;
    }



    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamTag(String teamTag) {
        this.teamTag = teamTag;
    }

    public void setTeamReg(String teamRegion) {
        this.teamRegion = teamRegion;
    }

    public void setTeamMail(String teamMail) {
        this.teamMail = teamMail;
    }

  

    @Override
    public String toString() {
        return "Teams{" + "teamId=" + teamId + ", teamLogo=" + teamLogo + ", teamName=" + teamName + ", teamTag=" + teamTag + ", teamRegion=" + teamRegion + ", teamMail=" + teamMail +  '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.teamId);
        hash = 17 * hash + Objects.hashCode(this.teamLogo);
        hash = 17 * hash + Objects.hashCode(this.teamName);
        hash = 17 * hash + Objects.hashCode(this.teamTag);
        hash = 17 * hash + Objects.hashCode(this.teamRegion);
        hash = 17 * hash + Objects.hashCode(this.teamMail);
        
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
        if (!Objects.equals(this.teamLogo, other.teamLogo)) {
            return false;
        }
        if (!Objects.equals(this.teamName, other.teamName)) {
            return false;
        }
        if (!Objects.equals(this.teamTag, other.teamTag)) {
            return false;
        }
        if (!Objects.equals(this.teamRegion, other.teamRegion)) {
            return false;
        }
        if (!Objects.equals(this.teamMail, other.teamMail)) {
            return false;
        }
        if (!Objects.equals(this.teamId, other.teamId)) {
            return false;
        }
       
        return true;
    }

   

   
    }

   
    
    


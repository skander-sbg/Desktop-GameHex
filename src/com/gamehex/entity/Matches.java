/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.entity;
import java.time.LocalDate;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import java.sql.Time;
import java.time.LocalTime;


/**
 *
 * @author Yasmine Daly
 */
public class Matches {
    
    private int id;
    private int team1_id;
    private int team2_id;
    private String match_res;
    private String match_com;
    private LocalDate match_date;
    private LocalTime match_time;
    public Matches() {
    }

    
    
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    public Matches(int matchId, int team1, int team2, String matchRes, String matchCom, LocalDate matchDate, LocalTime matchTime) {
        this.id = matchId;
        this.team1_id = team1;
        this.team2_id = team2;
        this.match_res = matchRes;
        this.match_com = matchCom;
        this.match_date = matchDate;
        this.match_time = matchTime;
    }

    public int getId() {
        return id;
    }

    public int getTeam1_id() {
        return team1_id;
    }

    public int getTeam2_id() {
        return team2_id;
    }

    public String getMatch_res() {
        return match_res;
    }

    public String getMatch_com() {
        return match_com;
    }

    public LocalDate getMatch_date() {
        return match_date;
    }

    public LocalTime getMatch_time() {
        return match_time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeam1_id(int team1_id) {
        this.team1_id = team1_id;
    }

    public void setTeam2_id(int team2_id) {
        this.team2_id = team2_id;
    }

    public void setMatch_res(String match_res) {
        this.match_res = match_res;
    }

    public void setMatch_com(String match_com) {
        this.match_com = match_com;
    }

    public void setMatch_date(LocalDate match_date) {
        this.match_date = match_date;
    }

    public void setMatch_time(LocalTime match_time) {
        this.match_time = match_time;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + this.team1_id;
        hash = 97 * hash + this.team2_id;
        hash = 97 * hash + Objects.hashCode(this.match_res);
        hash = 97 * hash + Objects.hashCode(this.match_com);
        hash = 97 * hash + Objects.hashCode(this.match_date);
        hash = 97 * hash + Objects.hashCode(this.match_time);
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
        final Matches other = (Matches) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.team1_id != other.team1_id) {
            return false;
        }
        if (this.team2_id != other.team2_id) {
            return false;
        }
        if (!Objects.equals(this.match_res, other.match_res)) {
            return false;
        }
        if (!Objects.equals(this.match_com, other.match_com)) {
            return false;
        }
        if (!Objects.equals(this.match_date, other.match_date)) {
            return false;
        }
        if (!Objects.equals(this.match_time, other.match_time)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Matches{" + "matchId=" + id + ", team1=" + team1_id + ", team2=" + team2_id + ", matchRes=" + match_res + ", matchCom=" + match_com + ", matchDate=" + match_date + ", matchTime=" + match_time + '}';
    }

    
    }

    

   

    
    
    
    


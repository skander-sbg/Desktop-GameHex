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
    
    private int matchId;
    private int team1;
    private int team2;
    private String matchRes;
    private String matchCom;
    private LocalDate matchDate;
    private LocalTime matchTime;
    public Matches() {
    }

    
    
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    public Matches(int matchId, int team1, int team2, String matchRes, String matchCom, LocalDate matchDate, LocalTime matchTime) {
        this.matchId = matchId;
        this.team1 = team1;
        this.team2 = team2;
        this.matchRes = matchRes;
        this.matchCom = matchCom;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getTeam1() {
        return team1;
    }

    public int getTeam2() {
        return team2;
    }

    public String getMatchRes() {
        return matchRes;
    }

    public String getMatchCom() {
        return matchCom;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public LocalTime getMatchTime() {
        return matchTime;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setTeam1(int team1) {
        this.team1 = team1;
    }

    public void setTeam2(int team2) {
        this.team2 = team2;
    }

    public void setMatchRes(String matchRes) {
        this.matchRes = matchRes;
    }

    public void setMatchCom(String matchCom) {
        this.matchCom = matchCom;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public void setMatchTime(LocalTime matchTime) {
        this.matchTime = matchTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.matchId;
        hash = 97 * hash + this.team1;
        hash = 97 * hash + this.team2;
        hash = 97 * hash + Objects.hashCode(this.matchRes);
        hash = 97 * hash + Objects.hashCode(this.matchCom);
        hash = 97 * hash + Objects.hashCode(this.matchDate);
        hash = 97 * hash + Objects.hashCode(this.matchTime);
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
        if (this.matchId != other.matchId) {
            return false;
        }
        if (this.team1 != other.team1) {
            return false;
        }
        if (this.team2 != other.team2) {
            return false;
        }
        if (!Objects.equals(this.matchRes, other.matchRes)) {
            return false;
        }
        if (!Objects.equals(this.matchCom, other.matchCom)) {
            return false;
        }
        if (!Objects.equals(this.matchDate, other.matchDate)) {
            return false;
        }
        if (!Objects.equals(this.matchTime, other.matchTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Matches{" + "matchId=" + matchId + ", team1=" + team1 + ", team2=" + team2 + ", matchRes=" + matchRes + ", matchCom=" + matchCom + ", matchDate=" + matchDate + ", matchTime=" + matchTime + '}';
    }

    
    }

    

   

    
    
    
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.entity;

import java.util.Objects;


/**
 *
 * @author Yasmine Daly
 */
public class TeamMembers {
    int riotId;
    String memberRole;
    int  memberPhone;
    String memberMail;
    int teamId;

    public TeamMembers(int riotId, String memberRole, int memberPhone, String memberMail, int teamId) {
        this.riotId = riotId;
        this.memberRole = memberRole;
        this.memberPhone = memberPhone;
        this.memberMail = memberMail;
        this.teamId = teamId;
    }

    public TeamMembers(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getRiotId() {
        return riotId;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public Integer getMemberPh() {
        return memberPhone;
    }

    public String getMemberMail() {
        return memberMail;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setRiotId(int riotId) {
        this.riotId = riotId;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public void setMemberPh(int memberPhone) {
        this.memberPhone = memberPhone;
    }

    public void setMemberMail(String memebrMail) {
        this.memberMail = memebrMail;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "TeamMembers{" + "riotId=" + riotId + ", memberRole=" + memberRole + ", memberPhone=" + memberPhone + ", memebrMail=" + memberMail + ", teamId=" + teamId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.riotId);
        hash = 47 * hash + Objects.hashCode(this.memberRole);
        hash = 47 * hash + Objects.hashCode(this.memberPhone);
        hash = 47 * hash + Objects.hashCode(this.memberMail);
        hash = 47 * hash + this.teamId;
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
        final TeamMembers other = (TeamMembers) obj;
        if (this.teamId != other.teamId) {
            return false;
        }
        if (!Objects.equals(this.memberRole, other.memberRole)) {
            return false;
        }
        if (!Objects.equals(this.memberMail, other.memberMail)) {
            return false;
        }
        if (!Objects.equals(this.riotId, other.riotId)) {
            return false;
        }
        if (!Objects.equals(this.memberPhone, other.memberPhone)) {
            return false;
        }
        return true;
    }

  
   
    
    
}

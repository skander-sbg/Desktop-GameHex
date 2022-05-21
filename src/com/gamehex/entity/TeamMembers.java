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
    int riot_id;
    String member_role;
    int  member_phone;
    String member_mail;
    int team_id;

    public TeamMembers(int riotId, String memberRole, int memberPhone, String memberMail, int teamId) {
        this.riot_id = riotId;
        this.member_role = memberRole;
        this.member_phone = memberPhone;
        this.member_mail = memberMail;
        this.team_id = teamId;
    }

    public TeamMembers(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getRiotId() {
        return riot_id;
    }

    public String getMember_role() {
        return member_role;
    }

    public Integer getMemberPh() {
        return member_phone;
    }

    public String getMember_mail() {
        return member_mail;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setRiot_id(int riot_id) {
        this.riot_id = riot_id;
    }

    public void setMember_role(String member_role) {
        this.member_role = member_role;
    }

    public void setMemberPh(int memberPhone) {
        this.member_phone = memberPhone;
    }

    public void setMember_mail(String memebrMail) {
        this.member_mail = memebrMail;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    @Override
    public String toString() {
        return "TeamMembers{" + "riotId=" + riot_id + ", memberRole=" + member_role + ", memberPhone=" + member_phone + ", memebrMail=" + member_mail + ", teamId=" + team_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.riot_id);
        hash = 47 * hash + Objects.hashCode(this.member_role);
        hash = 47 * hash + Objects.hashCode(this.member_phone);
        hash = 47 * hash + Objects.hashCode(this.member_mail);
        hash = 47 * hash + this.team_id;
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
        if (this.team_id != other.team_id) {
            return false;
        }
        if (!Objects.equals(this.member_role, other.member_role)) {
            return false;
        }
        if (!Objects.equals(this.member_mail, other.member_mail)) {
            return false;
        }
        if (!Objects.equals(this.riot_id, other.riot_id)) {
            return false;
        }
        if (!Objects.equals(this.member_phone, other.member_phone)) {
            return false;
        }
        return true;
    }

  
   
    
    
}

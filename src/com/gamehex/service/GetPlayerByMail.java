/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.gamehex.entity.TeamMembers;
import com.gamehex.utils.MyConnection;

/**
 *
 * @author Yasmine Daly
 */
public class GetPlayerByMail {

    public static final String ACCOUNT_SID = "AC90ac083ebfd6f6485124fb25d08fbbb0";
    public static final String AUTH_TOKEN = "d9964c385389b51c5fe3ae2464a8b1b0";

    public Integer GetuserBytel(String email) {
        int number = 0;
        try {
            TeamMembers captain = null;

            String requete = "Select memberPhone from teammembers where memberMail = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, email);
            ResultSet rs;
            rs = pst.executeQuery();
            if (rs.next()) {
                number = rs.getInt("memberPhone");
                captain = new TeamMembers(
                        rs.getInt("memberPhone"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return number;
    }

}

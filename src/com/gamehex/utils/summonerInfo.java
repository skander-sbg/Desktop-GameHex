/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.utils;

import com.merakianalytics.orianna.types.common.Queue;
import javafx.scene.image.ImageView;



/**
 *
 * @author Moudhaffer
 */
public class summonerInfo {

    private String summonerName;
    private String mostPlayedChamp;
    private Integer level;
    private ImageView profileIcon;
    private String rank;
    
    
//    System.out.println("Name: " + summoner.getName());
//    System.out.println("ID: " + summoner.getId());
//    System.out.println("Account ID: " + summoner.getAccountId());
//    System.out.println("Level: " + summoner.getLevel());
//    System.out.println("Last Updated: " + summoner.getUpdated());
//    System.out.println("Profile Icon ID: " + summoner.getProfileIcon().getId());
//    System.out.println("Profile Icon URL: " + summoner.getProfileIcon().getImage().getURL());
//    System.out.println("Highest rank: " + summoner.getHighestTier(Season.SEASON_8));

    public summonerInfo(String summonerName, String mostPlayedChamp, Integer level, String rank, ImageView profileIcon) {
        this.summonerName = summonerName;
        this.mostPlayedChamp = mostPlayedChamp;
        this.level = level;
        this.rank = rank;
        this.profileIcon = profileIcon;
    }
    public summonerInfo(){
        
    }
    

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getMostPlayedChamp() {
        return mostPlayedChamp;
    }

    public void setMostPlayedChamp(String mostPlayedChamp) {
        this.mostPlayedChamp = mostPlayedChamp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public ImageView getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(ImageView profileIcon) {
        this.profileIcon = profileIcon;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    
    
}

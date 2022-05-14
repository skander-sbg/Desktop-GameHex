/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Moudhaffer
 */
public class getSummoner implements Initializable {

    @FXML
    private JFXTextField txt_summoner;
    @FXML
    private JFXComboBox<String> jfxcb_region;
    @FXML
    private JFXTextField keywordTextField;
    @FXML
    private JFXButton btn_search;
    @FXML
    private Label summonerName_content;
    @FXML
    private Label level_content;
    @FXML
    private Label rank_content;
    @FXML
    private Label champ_content;
    @FXML
    private ImageView imgView_Champ;
    @FXML
    private ImageView imgView_Profile;
    @FXML
    private JFXButton btn_back;
    @FXML
    private Pane iconPane;
    @FXML
    private JFXButton btnHome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillJFXComboBox();
    }    
    
    @FXML
    private void handleSearchBtn(ActionEvent event) {
        if(jfxcb_region.getSelectionModel().isEmpty()){
            new animatefx.animation.Shake(jfxcb_region).play();
        }else{
            jfxcb_region.setStyle(null);
        }
        try{
            showSummoner();
        }catch(NullPointerException ex){
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/com/gamehex/view/warning_ui.fxml"));
                //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
                Scene scene = new Scene(root);
                stage.setTitle("Warning");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex1) {
                Logger.getLogger(getSummoner.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    private void fillJFXComboBox(){
//        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_éâàèû]*$");
//        UnaryOperator<TextFormatter.Change> filter = c -> {
//            if (pattern.matcher(c.getControlNewText()).matches()) {
//                return c ;
//            } else {
//                return null ;
//            }
//        };
//        TextFormatter<String> formatter = new TextFormatter<>(filter);
//        txt_summoner.setTextFormatter(formatter);
        List<String> listRegions = new ArrayList();
        listRegions.add("NORTH_AMERICA");
        listRegions.add("BRAZIL");        
        listRegions.add("EUROPE_NORTH_EAST");
        listRegions.add("EUROPE_WEST");
        listRegions.add("JAPAN");
        listRegions.add("KOREA");
        listRegions.add("LATIN_AMERICA_NORTH");
        listRegions.add("LATIN_AMERICA_SOUTH");
        listRegions.add("NORTH_AMERICA");
        listRegions.add("OCEANIA");
        listRegions.add("RUSSIA");
        listRegions.add("TURKEY");
        ObservableList<String> items = FXCollections.observableArrayList(listRegions);
        jfxcb_region.setItems(items);
    }
    
    private void showSummoner(){
        //ObservableList<summonerInfo> list = getSummonerList();
        //Summoner name label
        summonerName_content.setText(txt_summoner.getText());
        summonerName_content.setStyle("-fx-background-color:#273B56;");
        //Level label
        level_content.setText(getLvl().toString());
        level_content.setStyle("-fx-background-color:#273B56;");
        //Rank label
        rank_content.setText(getTier().toString());
        rank_content.setStyle("-fx-background-color:#273B56;");
        //Champ label
        champ_content.setText(mostPlayedChampion());
        champ_content.setStyle("-fx-background-color:#273B56;");
        //Summoner Icon imageview
//        root.getChildren().add(myGroup);
        imgView_Profile.setImage(getSummonerIcon());
        //Most played champion icon
        imgView_Champ.setImage(mostPlayedChampionIcon());
    }
    
//    public ObservableList<summonerInfo> getSummonerList(){
//        ObservableList<summonerInfo> summonerInfoList = FXCollections.observableArrayList();        
//        summonerInfo sumInfo = new summonerInfo(txt_summoner.getText(),
//                mostPlayedChampion(), getLvl(), getTier(), getSummonerIcon());
//        summonerInfoList.add(sumInfo);
//        System.out.println(summonerInfoList);
//        return summonerInfoList;
//    }
//    
    public Integer getLvl(){
        Summoner summ = getSummoner();
        return summ.getLevel();
    }
    
    public String mostPlayedChampion(){     
        Summoner summ = getSummoner();
        ChampionMasteries masteries = summ.getChampionMasteries();
        List<ChampionMastery> goodWith = masteries.filter((ChampionMastery mastery) -> mastery.getLevel() >= 6);
        List<String> names = goodWith.stream().map((ChampionMastery mastery) -> mastery.getChampion().getName()).collect(Collectors.toList());     
        return names.get(0);
    }
    
    public Image mostPlayedChampionIcon(){
        Summoner summ = getSummoner();
        ChampionMasteries masteries = summ.getChampionMasteries();
        List<ChampionMastery> goodWith = masteries.filter((ChampionMastery mastery) -> mastery.getLevel() >= 6);
        String imgURL = goodWith.get(0).getChampion().getImage().getURL();
        Image imChamp = new Image(imgURL);
        return imChamp;
        
    }
    
    
    public String getTier(){
        Summoner summ = getSummoner();
        String tier ="";
        try{
            tier = summ.getLeague(Queue.RANKED_SOLO).getTier().toString();
        }catch(NullPointerException ex){
            tier = "Unranked";
        }
        return tier;
    }
    
    public Image getSummonerIcon(){
        Summoner summ = Orianna.summonerNamed(txt_summoner.getText()).get();
        String url = summ.getProfileIcon().getImage().getURL();
        Image imProfile = new Image(url);
        //ImageView icon = new ImageView(new Image(this.getClass().getResourceAsStream("src/resources/img/icon.jpg")));
        return imProfile;
    }
    
    private Summoner getSummoner(){
        Orianna.setRiotAPIKey("RGAPI-efa44a94-511b-4238-81e3-1cf691756754");
        Orianna.setDefaultRegion(Region.valueOf(jfxcb_region.getValue()));
        if (validateUsername()){
            Summoner summoner = Summoner.named(txt_summoner.getText()).get();
            return summoner; 
        }
        return null;
    }
    private boolean validateUsername() {
        Pattern p = Pattern.compile("[a-zA-Z0-9_]+");
        Matcher m = p.matcher(txt_summoner.getText());
        if ((txt_summoner.getText().length() == 0) || (!m.find() && m.group().equals(txt_summoner.getText()))) {
            new animatefx.animation.Shake(txt_summoner).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            txt_summoner.setEffect(in);
            return false;
        } else {
            txt_summoner.setEffect(null);
            return true;
        }
    }

    @FXML
    private void handleBackBtn(ActionEvent event) {
        if(event.getSource() == btn_back){
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/com/gamehex/view/Home_Screen.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("Home Screen");
                stage.setScene(scene);
                stage.show();
                Stage stage2 = (Stage) btn_back.getScene().getWindow();
                stage2.close();
            } catch (IOException ex) {
                Logger.getLogger(getSummoner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void OnHomeClicked(ActionEvent event) throws IOException {
        
         FXMLLoader fxmlLoader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/Home.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Home");
        //stage.getIcons().add(new Image("com/gamehex/assets/NotePad.png"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

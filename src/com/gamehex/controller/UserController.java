/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.gamehex.utils.MyConnection;
import com.gamehex.entity.Session;
import com.gamehex.entity.User;
import com.gamehex.service.UsersService;

/**
 *
 * @author asus
 */
public class UserController implements Initializable {
    
    

    @FXML
    private TextField tfCIN;

    @FXML
    private DatePicker tfDate;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPWD;

    @FXML
    private TextField tfPhone;

    @FXML
    private TableColumn<User, Integer> tvCIN;

    @FXML
    private TableColumn<User, String> tvDate;

    @FXML
    private TableColumn<User, String> tvEmail;

    @FXML
    private TableColumn<User, Integer> tvID;

    @FXML
    private TableColumn<User, String> tvLastName;

    @FXML
    private TableColumn<User, String> tvName;

    @FXML
    private TableColumn<User, String> tvPWD;

    @FXML
    private TableColumn<User, Integer> tvPhone;

    @FXML
    private TableView<User> tvUsers;

    @FXML
    private Button add;
    
    @FXML
    private Button update;

    @FXML
    private Button delete;
    @FXML
    private Button up;
    @FXML
    private Button down;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button stat;
    private Button article;
    @FXML
    private Button signout;
    @FXML
    private Label labelName;
    

    @FXML
    void initialize(ActionEvent event) {

    }
    
     Connection cnx2;
    public UserController(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    private void dispayutils(ObservableList<User> listUser) {
        tvID.setCellValueFactory(new PropertyValueFactory<User, Integer>("userID"));
        tvName.setCellValueFactory(new PropertyValueFactory<User , String>("name"));
        tvLastName.setCellValueFactory(new PropertyValueFactory<User , String>("lastName"));
        tvCIN.setCellValueFactory(new PropertyValueFactory<User , Integer>("CIN"));
        tvPhone.setCellValueFactory(new PropertyValueFactory<User , Integer>("phone"));
        tvDate.setCellValueFactory(new PropertyValueFactory<User , String>("date"));
        tvEmail.setCellValueFactory(new PropertyValueFactory<User , String>("email"));
        tvPWD.setCellValueFactory(new PropertyValueFactory<User , String>("pwd"));
        tvUsers.setItems(listUser);
    }
    
    public void showUser(){
        UsersService us = new UsersService();
        ObservableList<User> listUser = us.displayUser() ;
        dispayutils(listUser);
        System.out.println(listUser);
    }
    
    /*public void showUser(){
        UsersService us = new UsersService();
        ObservableList<User> listUser = us.displayUserSort() ;
        dispayutils(listUser);
        System.out.println(listUser);
    }*/
    
    public void sortUPUser(){
        UsersService us = new UsersService();
        ObservableList<User> listUser = us.sortUP() ;
        dispayutils(listUser);
        System.out.println(listUser);
    }
    
    public void sortDOWNUser(){
        UsersService us = new UsersService();
        ObservableList<User> listUser = us.sortDOWN() ;
        dispayutils(listUser);
        System.out.println(listUser);
    }
    
    
    //********* session **********
    User u=Session.StartSession().getSessionUser();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         labelName.setText("WELCOME   "+u.getName()+" "+u.getLastName());
        
        showUser();
        
        add.setOnAction(event->{
            if( ((tfName.getText().isEmpty()) || (tfLastName.getText().isEmpty()) || (tfCIN.getText().isEmpty())
                   || (tfPhone.getText().isEmpty()) || (tfDate.getValue()==null)
                   || (tfEmail.getText().isEmpty()) || (tfPWD.getText().isEmpty())) ){
               
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("User can not be created !!! ");
               alert.setContentText("Please fill in the fields !!! ");
               alert.show(); 
            }
           else if((!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", tfCIN.getText()))
                   || (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", tfPhone.getText()))){
                
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("User can not be created !!! ");
                   alert.setContentText("CIN and Phone must be formed with numbers only ( 8 numbers to be specific) !!! ");
                   alert.show(); 
               }
           else if(!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", tfEmail.getText())){
                
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("User can not be created !!! ");
                   alert.setContentText("Email format is not correct !!! ");
                   alert.show(); 
               }
           else{
            int CIN = Integer.parseInt(tfCIN.getText());
            int phone = Integer.parseInt(tfPhone.getText());
            User u = new User(tfName.getText(),
                              tfLastName.getText(),
                              CIN,
                              phone,
                              tfDate.getValue().toString(),
                              tfEmail.getText(),
                              tfPWD.getText());
            
            UsersService us = new UsersService();
            us.addUser(u);
            tfName.setText("");
            tfLastName.setText("");
            tfCIN.setText("");
            tfPhone.setText("");
            //tfDate.setValue("");
            tfEmail.setText("");
            tfPWD.setText("");
            showUser();
           }
        });
        
        delete.setOnAction(event->{
            User u = (User)tvUsers.getSelectionModel().getSelectedItem();
            UsersService us = new UsersService();
            us.deleteUser(u.getUserID());
            tfName.setText("");
            tfLastName.setText("");
            tfCIN.setText("");
            tfPhone.setText("");
            //tfDate.setValue("");
            tfEmail.setText("");
            tfPWD.setText("");
            showUser();
        });
        
        update.setOnAction(event->{
            User u = (User)tvUsers.getSelectionModel().getSelectedItem();
            if( ((tfName.getText().isEmpty()) || (tfLastName.getText().isEmpty()) || (tfCIN.getText().isEmpty())
                   || (tfPhone.getText().isEmpty()) || (tfDate.getValue()==null)
                   || (tfEmail.getText().isEmpty()) || (tfPWD.getText().isEmpty())) ){
               
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("User can not be updated !!! ");
               alert.setContentText("Please fill in the filds !!! ");
               alert.show(); 
            }
            else if((!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", tfCIN.getText()))
                   || (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", tfPhone.getText()))){
                
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("User can not be update !!! ");
                   alert.setContentText("CIN and Phone must be formed with numbers only ( 8 numbers to be specific) !!! ");
                   alert.show(); 
               }
            else if(!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", tfEmail.getText())){
                
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("User can not be updated !!! ");
                   alert.setContentText("Email format is not correct !!! ");
                   alert.show(); 
               }
            else{
            UsersService us = new UsersService();
            int CIN = Integer.parseInt(tfCIN.getText());
            int phone = Integer.parseInt(tfPhone.getText());
            User u2 = new User(u.getUserID(),tfName.getText(),
                               tfLastName.getText(),
                               CIN,
                               phone,
                               tfDate.getValue().toString(),
                               tfEmail.getText(),
                               tfPWD.getText());
            
            us.updateUser(u2);
            tfName.setText("");
            tfLastName.setText("");
            tfCIN.setText("");
            tfPhone.setText("");
            //tfDate.setValue("");
            tfEmail.setText("");
            tfPWD.setText("");
            showUser();
            }
        });
        
        
        up.setOnAction(event->{
            sortUPUser();  
        });
        
        down.setOnAction(event->{
            sortDOWNUser();
        });
        
        
        tfSearch.textProperty().addListener((obj,old,ne) -> {
            UsersService us = new UsersService();
            if (!tfSearch.getText().isEmpty())
                tvUsers.setItems(us.searchByNameCIN(ne));
            else 
                tvUsers.setItems(us.displayUser());  
        });
        
        stat.setOnAction(event->{
            try {
                 FXMLLoader fxmlLoader = new FXMLLoader();
                 Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/chartFXML.fxml"));
                 Scene scene = new Scene(parent);
                 Stage stage = new Stage();
                 stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
                 } catch (IOException ex) {
                 System.out.println("error in displaying the interface");
                 }  
        });
        
        
       /* article.setOnAction(event->{
            try {
             
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/ArticleFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying the interface");
            }  
        });*/
        
        signout.setOnAction(event->{
            try {
                 FXMLLoader fxmlLoader = new FXMLLoader();
                 Parent parent = FXMLLoader.load(getClass().getResource("/com/gamehex/view/loginFXML.fxml"));
                 Scene scene = new Scene(parent);
                 Stage stage = new Stage();
                 stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
                } catch (IOException ex) {
                System.out.println("error in displaying the interface");
                }  
        });
        
    }    
    
    int index=-1;
    @FXML
    private void forUpdate(MouseEvent event) {
        index =tvUsers.getSelectionModel().getSelectedIndex();
   if(index<= -1){
    return ;
    }
   tfName.setText(tvName.getCellData(index));
   tfLastName.setText(tvLastName.getCellData(index));
   tfCIN.setText(tvCIN.getCellData(index).toString());
   tfPhone.setText(tvPhone.getCellData(index).toString());
   tfEmail.setText(tvEmail.getCellData(index));
   tfPWD.setText(tvPWD.getCellData(index));
    
    }
    

    
   /* private void print(ActionEvent event) {
            try{
                
                JasperDesign jasperDesign = JRXmlLoader.load("D:\\uni\\esprit 3\\semester 2\\PIDev\\java\\final\\src\\print\\report3.jrxml");
                
                String sql="SELECT userID, name, lastName, CIN, phone, date, email, pwd FROM user ";
                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);
     
                jasperDesign.setQuery(newQuery);
                
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, cnx2);
                JasperViewer.viewReport(jasperPrint,false);
            } catch (JRException ex) {
                System.out.println(ex.getMessage());
                
            }
        
    }*/

    
    
   /* UsersService us=new UsersService();
        CategoryAxis xAxis=new CategoryAxis(FXCollections.observableArrayList(us.displayUser()
                                            .stream().map(g -> g.getName()).distinct().collect(Collectors.toList())));
        
        
       
        xAxis.setLabel("User");
        xAxis.setGapStartAndEnd(true);
        
        NumberAxis yAxis=new NumberAxis();
        yAxis.setLabel("Count");
        BarChart<String,Number> bchart=new BarChart(xAxis,yAxis);
        
        bchart.setTitle("Statistics");
        
        ObservableList<Article> listg=GamesDao.getInstance().DisplayObservableList();
        for (String d: diff){
            XYChart.Series<String, Number> s=new XYChart.Series<>();
            s.setName(d);
            for (Games g : listg){
            s.getData().add(new XYChart.Data<>(g.getName(),countTrophy(g, d)));   
            }
           
            bchart.getData().add(s);
}*/
    

    
    
}

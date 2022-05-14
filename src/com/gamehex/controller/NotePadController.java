/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import com.gamehex.entity.Note;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.gamehex.model.IConversation;
import com.gamehex.model.IMemento_Command;
import com.gamehex.model.Memento_Command;
import com.gamehex.model.Travaux;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author Yasmine Daly
 */
public class NotePadController implements Initializable {

    int i=1;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem newFile;
    @FXML
    private MenuItem openFile;
    @FXML
    private MenuItem saveAsFile;
    @FXML
    private MenuItem exit;
    @FXML
    private ListView<Note> list;
    @FXML
    private TextField titleText;
    @FXML
    private Button createButton;
    @FXML
    private Button deletedButton;
    @FXML
    private TextArea contentText;
    
    private File fileSelect = null;

    public static Stage stage = new Stage();
   
    IConversation<IMemento_Command> commands = new Memento_Command();
    private final ObjectProperty<Travaux> leModele = new SimpleObjectProperty<>(new Travaux());
    
    public Travaux getLeModele() {return leModele.get();};
        public void setLeModele(Travaux param) {leModele.set(param);}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list.setCellFactory((param) -> {
            return new ListCell<Note>(){
               @Override
                protected void updateItem(Note item, boolean empty) {
                    super.updateItem(item, empty);
                     int i=0;
                    if (! empty) {
                        textProperty().bind(item.nameNoteProperty());
                     
                    }else {
                        textProperty().unbind();
                        setText("");
                    }
                }  
            };
        });
         
        
        list.getSelectionModel().selectedItemProperty().addListener((o,old,newV)->{
            if (old != null) {
                contentText.textProperty().unbindBidirectional(old.texteProperty());
                titleText.textProperty().unbindBidirectional(old.nameNoteProperty());
                
            }
            if (newV != null) {
                contentText.textProperty().bindBidirectional(newV.texteProperty());
                titleText.textProperty().bindBidirectional(newV.nameNoteProperty());
                
                list.getSelectionModel().getSelectedItem().texteProperty().addListener((ac,vi,no)->{
                    this.clicSauvegarder();
                    commands.exec(Note(list.getSelectionModel().getSelectedItem().getTexte()));
                });
                
                
            }
            
        });
        
        this.clicOuvrirAll();
        
        KeyCombination keyCombination = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
        this.newFile.setAccelerator(keyCombination);
        keyCombination = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
        this.openFile.setAccelerator(keyCombination);
        keyCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        this.saveAsFile.setAccelerator(keyCombination);
        keyCombination = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
        this.exit.setAccelerator(keyCombination);
    }    

    @FXML
    private void newFile(ActionEvent event) {
        this.onCreateButton(event);
    }

    @FXML
    private void openFile(ActionEvent event) {
        this.clicOuvrir();
    }

    @FXML
    private void saveAsFile(ActionEvent event) {
        this.clicEnregistrer();
    }

    @FXML
    private void exitApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void undo(ActionEvent event) {
        commands.undo();
    }

    @FXML
    private void redo(ActionEvent event) {
        commands.redo();
    }

    @FXML
    private void cut(ActionEvent event) {
        this.contentText.cut();
    }

    @FXML
    private void copy(ActionEvent event) {
        this.contentText.copy();
    }

    @FXML
    private void paste(ActionEvent event) {
        this.contentText.paste();
    }

    @FXML
    private void delete(ActionEvent event) {
        this.contentText.deleteNextChar();
    }

    @FXML
    private void selectAll(ActionEvent event) {
        this.contentText.selectAll();
    }

    @FXML
    private void fullscreen(ActionEvent event) {
        Stage stage = (Stage) this.contentText.getScene().getWindow();
            stage.setFullScreen(true);
    }

    @FXML
    private void onCreateButton(ActionEvent event) {
        this.createNote();
    }

    @FXML
    private void onDeletedButton(ActionEvent event) {
        this.clicDelete();
    }
    
    
    
    protected IMemento_Command Note (String stringToType) {
        String name = list.getSelectionModel().getSelectedItem().getNameNote();
        String contenu = stringToType;
        File fichier = list.getSelectionModel().getSelectedItem().getFile();
            return new Note(name,contenu,fichier);
    }  

private void createNote () {
        Note n = new Note("Sans Titre "+i,"",new File("./Sans Titre "+i+".txt"));
        list.getItems().add(n);
        i++;        
        try {
           n.getFile().createNewFile();
        } catch(IOException e) {
                e.printStackTrace();
        }
    }
    

    private void clicSauvegarder () {
        
        String content = list.getSelectionModel().getSelectedItem().getTexte();
        String title = list.getSelectionModel().getSelectedItem().getNameNote();
        title = title.concat(".txt");
        File f = list.getSelectionModel().getSelectedItem().getFile().getAbsoluteFile();
        Note n = list.getSelectionModel().getSelectedItem();
        
       
        n.seSauvergarder(new Note (title,content,f));
          
    }
        
    private void clicEnregistrer() {
        
        int index = list.getSelectionModel().getSelectedIndex();
        
        if (index !=-1) {

            DirectoryChooser fileChooser = new DirectoryChooser();
            fileChooser.setTitle("Selectionner un répertoire");

            File fichierSelectionne = fileChooser.showDialog(null);

            String content = this.contentText.getText();
            String title = this.titleText.getText();
            title = title.concat(".txt");


            if(fichierSelectionne != null) {

                fichierSelectionne = new File(fichierSelectionne.getAbsolutePath(), title);

                try {
                        if(!fichierSelectionne.exists())
                                fichierSelectionne.createNewFile();

                        FileWriter fileWriter = new FileWriter(fichierSelectionne.getAbsoluteFile());
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write(content);
                        bufferedWriter.close();

                } catch(Exception e) {
                        e.printStackTrace();
                }
            } else {
                showMessage(Alert.AlertType.ERROR, null, "If you want to save your file, you need to open it");
            }
            
        }else{
            showMessage(Alert.AlertType.ERROR, null, "Aucun élément selectionné");
        } 
    }

    private void clicOuvrir() {
/*
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir un fichier");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File fichierSelectionne = fileChooser.showOpenDialog(Main.getPrimaryStage());
        
        String name = fichierSelectionne.getName();
        name = name.substring(0,name.indexOf("."));
        
        
        StringBuilder contenu = new StringBuilder();
        
        boolean trouve=false;
        
        for (Note n : list.getItems() ) {
            if (n.getFile().getAbsolutePath().equals(fichierSelectionne.getAbsolutePath())){
                showMessage(Alert.AlertType.ERROR, null, "Element est déjà là");
                trouve=true;
            }
        }
        
        if(fichierSelectionne != null && !trouve ) {

                BufferedReader bufferedReader = null;
                
                try {
                        String currentLine;
                        
                        bufferedReader = new BufferedReader(new FileReader(fichierSelectionne));
                        while((currentLine = bufferedReader.readLine()) != null)
                                contenu.append(currentLine + "\n");
                        
                        Note n = new Note(name,contenu.toString(),fichierSelectionne.getAbsoluteFile());
                        list.getItems().add(n);
                        
                } catch(Exception e) {
                        e.printStackTrace();
                }
        }
        */
        
    }
    
    private void clicOuvrirAll() {

        ArrayList<File> files = new ArrayList<File>();

        File repo = new File ("./");

        File[] fileList = repo.listFiles();
        
        for (File f : fileList) {
           if(f.getName().contains(".txt")){
               
                BufferedReader bufferedReader = null;
                StringBuilder contenu = new StringBuilder();
                String name = f.getName();
                name = name.substring(0,name.indexOf("."));
                
                try {
                        String currentLine;
                        
                        bufferedReader = new BufferedReader(new FileReader(f));
                        while((currentLine = bufferedReader.readLine()) != null)
                                contenu.append(currentLine + "\n");
                        
                        list.getItems().add(new Note(name,contenu.toString(),f.getAbsoluteFile()));
                        
                } catch(Exception e) {
                        e.printStackTrace();
                }
           }
        }
        
        
    }
    
    private void clicDelete () { //quand on veut supprimer le deuxieme + supprimer fichier ouver
        
        int index;

        if (list.getSelectionModel().getSelectedIndex() > -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fenêtre de confirmation");
            alert.setHeaderText("Attention !");
            alert.setContentText("Voulez-vous supprimer l'élement sélectionné ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                list.getSelectionModel().getSelectedItem().getFile().getAbsoluteFile().delete();
                list.getItems().remove(list.getSelectionModel().getSelectedIndex());
                this.titleText.clear();
                this.contentText.clear();
            }
        } else {
            showMessage(Alert.AlertType.ERROR, null, "Element non sélectionné");
        }
        
        
    }
    
    

    private Optional<ButtonType> showMessage(Alert.AlertType type,String header,String message,ButtonType... lesBoutonsDifferents){
        Alert laFenetre = new Alert(type);
        laFenetre.setHeaderText(header);
        laFenetre.setContentText(message);
        if (lesBoutonsDifferents.length > 0) {
            laFenetre.getButtonTypes().clear();
            laFenetre.getButtonTypes().addAll(lesBoutonsDifferents);
        }
        return laFenetre.showAndWait();
    }
    
    
}


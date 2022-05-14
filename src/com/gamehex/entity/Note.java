/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.entity;

/**
 *
 * @author Yasmine Daly
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import com.gamehex.model.IMemento_Command;
import com.gamehex.model.IMemento;


public class Note implements IMemento_Command  {
    
    private final StringProperty nameNote = new SimpleStringProperty();
        public String getNameNote() { return nameNote.get();}
        public void setNameNote(String value) { nameNote.set(value); }
        public StringProperty nameNoteProperty() { return nameNote; }
    
    private final StringProperty texte = new SimpleStringProperty();
        public String getTexte() { return texte.get();}
        public void setTexte(String value) { texte.set(value); }
        public StringProperty texteProperty() { return texte; }
        
    private final ObjectProperty<File> myFile = new SimpleObjectProperty<File>();
        public File getFile() { return myFile.get();}
        public void setFile(File value) { myFile.set(value); }
        public ObjectProperty<File> fileProperty() { return myFile; }
        
    
    public Note (String name, String txt, File fich) {
        nameNote.set(name);
        texte.set(txt);
        myFile.set(fich);
    }
    
    public void seSauvergarder (Note n)  {
        
        String title = n.getNameNote();
        File fich;
        
        
        
        fich = new File (n.getFile().getParent().concat("./NotePadMax/"+title));
      

        n.getFile().delete();
        
        try {
                        
                FileWriter fileWriter = new FileWriter(fich.getAbsoluteFile());
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(n.getTexte());
                bufferedWriter.close();
        } catch(Exception e) {
                e.printStackTrace();
        }
        
    }

    @Override
    public IMemento takeSnapshot() {
        final String snapshot =  this.getTexte();
            return () -> this.setState(snapshot);
    }
    
    
    private void setState(String elmnts) {
        this.setTexte(elmnts);
    }
    

    @Override
    public void execute() {
        this.setTexte(this.getTexte());
    }
 
}

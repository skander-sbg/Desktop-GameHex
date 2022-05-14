/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.model;

import com.gamehex.entity.Note;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author maxime
 */
public class Travaux{

    private final ObservableList<Note> lesTravauxObservables = FXCollections.observableArrayList();
    
    private final ListProperty<Note> lesTravaux = new SimpleListProperty<>(lesTravauxObservables);
        public ObservableList<Note> getLesTravaux() {return lesTravaux.get();}
        public ReadOnlyListProperty<Note> lesTravauxProperty() {return lesTravaux;}

    public Travaux(){
        
    }
    
}

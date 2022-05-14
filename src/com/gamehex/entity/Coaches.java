/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.entity;

import java.util.Objects;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Moudhaffer
 */
public class Coaches {
    private int coachId;
    private Float rating; 
    private int userID;

    public Coaches() {
    }
    
    

    public Coaches(int coachId, Float rating, User user) {
        this.coachId = coachId;
        this.rating = rating;

    }

    public Coaches(int coachId, Float rating) {
        this.coachId = coachId;
        this.rating = rating;
    }
    

    public int getCoachId() {
        return coachId;
    }

    public Float getRating() {
        return rating;
    }

   
    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Coaches{" + "coachId=" + coachId + ", rating=" + rating + '}';
    }

    

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.coachId;
        hash = 83 * hash + Objects.hashCode(this.rating);
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
        final Coaches other = (Coaches) obj;
        if (this.coachId != other.coachId) {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) {
            return false;
        }
        
        return true;
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.entity;

/**
 *
 * @author asus
 */
public final class Session {
    
    private static Session instance;
     private User SessionUser;

   
     private Session() {
     }
      private Session(User SessionUser) {
          this.SessionUser=SessionUser;
     }
     
     public static Session StartSession() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }
     
     public static Session StartSession(User SessionUser) {
        if(instance == null) {
            instance = new Session(SessionUser);
        }
        return instance;
    } 
     public static Session getSession() {
        return instance;
    }
      public  void clearSession() {
      SessionUser=null;

      }
      public void setSessionUser(User SessionUser){
      this.SessionUser=SessionUser;
      }
      public User getSessionUser(){
      return this.SessionUser;
      }
    
    
}

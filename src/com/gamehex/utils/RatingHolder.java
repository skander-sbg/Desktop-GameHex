/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.utils;

/**
 *
 * @author Yasmine Daly
 */
public final class RatingHolder {
  
  private int rating;
  private final static RatingHolder INSTANCE = new RatingHolder();
  
  private RatingHolder() {}
  
  public static RatingHolder getInstance() {
    return INSTANCE;
  }
  
  public void setUser(int u) {
    this.rating = u;
  }
  
  public int getUser() {
    return this.rating;
  }
}

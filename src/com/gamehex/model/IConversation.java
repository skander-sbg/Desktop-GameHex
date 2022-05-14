/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.model;

/**
 *
 * @author maxime
 */
public interface IConversation <C extends ICommand> {
    
  public void exec(C cmd);
  public void undo();
  public void redo();
  
}

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
public class BeforeAfter {
 
    final IMemento before, after;
 
    /**
    * @param before must be immutable
    * @param after  must be immutable
    */
    public BeforeAfter(IMemento before, IMemento after) {
        this.before = before;
        this.after = after;
    }
}
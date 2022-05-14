/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.model;


import java.util.Stack;


/**
 *
 * @author maxime
 */
public class Memento_Command extends AbstractConversation<IMemento_Command, BeforeAfter> {
    
    public void exec(IMemento_Command todo) {
        IMemento before = todo.takeSnapshot();
        todo.execute();
        IMemento after = todo.takeSnapshot();
 
        undos.push(new BeforeAfter(before, after));
        redos.clear();
    }
 
    public void undo() {
        BeforeAfter latestIMemento = undos.pop();
        if(latestIMemento==null) return;
        IMemento latestBefore = latestIMemento.before;
        latestBefore.restore();
        redos.push(latestIMemento);
    }
 
    public void redo() {
        BeforeAfter latestIMemento = redos.pop();
        if(latestIMemento==null) return;
        IMemento latestAfter = latestIMemento.after;
        latestAfter.restore();
        undos.push(latestIMemento);
    }
}
 

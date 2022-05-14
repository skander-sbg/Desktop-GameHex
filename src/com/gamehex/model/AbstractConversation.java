/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

/**
 *
 * @author maxime
 */
public abstract class AbstractConversation<C extends ICommand, S> implements IConversation<C> {
    protected final Stack<S> undos, redos;
 
    public AbstractConversation() {
        this.undos= new Stack<S>();
        this.redos= new Stack<S>();
    }
    
    public class Stack<T> {
 
        //Delegate to avoid exposing too many Deque methods
        private final Deque<T> stack = new LinkedList<>();

        /**
         * @return null if stack is empty
         */
        public T pop() {
            return stack.pollLast(); //Not using pop since it throws NoSuchElementException if the deque is empty
        }

        public void push(T cmd) {
            stack.addLast(cmd);
        }

        public void clear() {
            stack.clear();
        }

        public void forEachFifo(Consumer<? super T> action) {
            stack.stream().forEachOrdered(action);
        }
    }
}

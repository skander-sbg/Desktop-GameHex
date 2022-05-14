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
public class Article {
    
    private int articleID;
    private String content;
    

    
    public Article(){
        
    }

    public Article(String content) {
        this.content = content;
    }

    public Article(int articleID, String content) {
        this.articleID = articleID;
        this.content = content;
    }
    
    

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" + "articleID=" + articleID + ", content=" + content + '}';
    }


    
}

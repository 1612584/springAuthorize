package com.example.demo.model;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class News implements Serializable {
    public String title;
    public String content;
    public String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

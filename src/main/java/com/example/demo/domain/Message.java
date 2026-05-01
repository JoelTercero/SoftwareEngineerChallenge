package com.example.demo.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class Message {

    @NotNull
    private Category category;

    @NotBlank
    private String content;

    public Message() {
    }

    public Message(Category category, String content) {
        this.category = category;
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

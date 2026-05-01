package com.example.demo.domain;

import java.util.List;

public class User {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<Category> subscribed;
    private List<Channel> channels;

    public User() {
    }

    public User(Long id, String name, String email, String phone,
                List<Category> subscribed, List<Channel> channels) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subscribed = subscribed;
        this.channels = channels;
    }

    public boolean isSubscribedTo(Category category) {
        return subscribed != null && subscribed.contains(category);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<Category> getSubscribed() {
        return subscribed;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSubscribed(List<Category> subscribed) {
        this.subscribed = subscribed;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}

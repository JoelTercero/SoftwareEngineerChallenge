package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<Category> subscribed;
    private List<Channel> channels;

    public boolean isSubscribedTo(Category category) {
        return subscribed != null && subscribed.contains(category);
    }

}

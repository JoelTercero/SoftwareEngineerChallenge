package com.example.demo.repository;

import com.example.demo.domain.Category;
import com.example.demo.domain.Channel;
import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    public List<User> getUsers() {
        return List.of(
                new User(1L, "Joel", "joel@mail.com", "123456",
                        List.of(Category.SPORTS, Category.MOVIES),
                        List.of(Channel.EMAIL, Channel.SMS)),

                new User(2L, "Ana", "ana@mail.com", "999999",
                        List.of(Category.FINANCE),
                        List.of(Channel.PUSH))
        );
    }
}

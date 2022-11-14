package com.example.playerservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Player {

    @Id
    private String id;
    private String username;
    private String email;
    private List<String> friendIds;

    public Player(String username, String email, List<String> friendIds) {
        this.username = username;
        this.email = email;
        this.friendIds = friendIds;
    }
}

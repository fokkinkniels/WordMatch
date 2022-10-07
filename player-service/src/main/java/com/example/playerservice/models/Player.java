package com.example.playerservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Player {

    @Id
    private String id;
    private String name;
    private String email;
    private List<String> friendIds;

    public Player(String name, String email, List<String> friendIds) {
        this.name = name;
        this.email = email;
        this.friendIds = friendIds;
    }
}

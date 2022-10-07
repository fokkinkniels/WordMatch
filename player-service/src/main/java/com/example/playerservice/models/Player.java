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
    private List<String> friend_ids;

    public Player(String name, String email, List<String> friend_ids) {
        this.name = name;
        this.email = email;
        this.friend_ids = friend_ids;
    }
}

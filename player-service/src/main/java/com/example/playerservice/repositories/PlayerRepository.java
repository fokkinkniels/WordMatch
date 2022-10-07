package com.example.playerservice.repositories;

import com.example.playerservice.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlayerRepository extends MongoRepository<Player, String> {
    List<Player> findAllByIdIsIn(List<String> ids);
}

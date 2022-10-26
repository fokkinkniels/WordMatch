package com.example.playerservice.repositories;

import com.example.playerservice.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, String> {
    List<Player> findAllByIdIsIn(List<String> ids);
    List<Player> findPlayerByfriendIdsContains (String id);
    Player findPlayersByEmail(String email);
}

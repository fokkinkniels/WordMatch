package com.example.playerservice.services;

import com.example.playerservice.models.Player;
import com.example.playerservice.repositories.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    //Get Services

    public ResponseEntity<List<Player>> GetAllPlayers() {
        return new ResponseEntity<List<Player>>(playerRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Player> GetPlayerById(String id) {
        var player = playerRepository.findById(id);
        return player.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    public ResponseEntity<List<Player>> GetPLayerFriends(String id) {
        var friends = playerRepository.findById(id).get().getFriend_ids();
        return new ResponseEntity<List<Player>>(playerRepository.findAllByIdIsIn(friends), HttpStatus.OK);
    }
}

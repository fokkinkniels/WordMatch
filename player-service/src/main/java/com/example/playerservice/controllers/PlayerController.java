package com.example.playerservice.controllers;

import com.example.playerservice.models.Player;
import com.example.playerservice.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/player")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    //Get Endpoints

    @GetMapping("/all")
    public ResponseEntity<List<Player>> FetchAllPlayer() {
        return playerService.GetAllPlayers();
    }

    @GetMapping()
    public ResponseEntity<Player> FetchPlayerById(@RequestParam String id){
        return playerService.GetPlayerById(id);
    }

    @GetMapping("/friends")
    public ResponseEntity<List<Player>> FetchPlayerFriends (@RequestParam String id){
        return playerService.GetPLayerFriends(id);
    }
}

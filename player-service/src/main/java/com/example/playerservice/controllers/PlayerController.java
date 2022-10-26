package com.example.playerservice.controllers;

import com.example.playerservice.models.Player;
import com.example.playerservice.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080/", maxAge = 3600)
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

    @GetMapping("/{id}")
    public ResponseEntity<Player> FetchPlayerById(@PathVariable String id){
        return playerService.GetPlayerById(id);
    }

    @GetMapping("/friends/{id}")
    public ResponseEntity<List<Player>> FetchPlayerFriends(@PathVariable String id){
        return playerService.GetPLayerFriends(id);
    }


    //Post Endpoints

    @PostMapping("/create")
    public ResponseEntity<String> CreateNewPlayer(@RequestBody Player player){
        return playerService.CreateNewPLayer(player);
    }

    @PostMapping("/login")
    public ResponseEntity<Player> LoginPlayer(@RequestBody String email){
        return playerService.LoginPLayer(email);
    }


    //Put Endpoints

    @PutMapping("/friends/add")
    public ResponseEntity<String> AddNewFriend(@RequestBody List<String> ids){
        return playerService.AddNewFriend(ids);
    }

    @PutMapping("/friends/remove")
    public ResponseEntity<String> RemoveFriend(@RequestBody List<String> ids){
        return playerService.RemoveFriend(ids);
    }

    @PutMapping("/update")
    public ResponseEntity<String> UpdatePlayer(@RequestBody Player player){
        return playerService.UpdatePLayer(player);
    }


    //Delete Endpoints

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> RemovePlayer(@PathVariable String id){
        return playerService.RemovePlayer(id);
    }
}
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
        return new ResponseEntity<>(playerRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Player> GetPlayerById(String id) {
        var player = playerRepository.findById(id);
        return player.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    public ResponseEntity<List<Player>> GetPLayerFriends(String id) {
        var friends = playerRepository.findById(id).get().getFriendIds();
        return new ResponseEntity<>(playerRepository.findAllByIdIsIn(friends), HttpStatus.OK);
    }


    //Post Services

    public ResponseEntity<String> CreateNewPLayer(Player player) {
        if (player.getId() != null){
            return new ResponseEntity<>("Player already exists", HttpStatus.BAD_REQUEST);
        }
        playerRepository.save(player);
        return new ResponseEntity<>("Player has been saved! id:"+ player.getId(), HttpStatus.OK);
    }


    //Put Services

    public ResponseEntity<String> AddNewFriend(List<String> ids) {
        String playerId = ids.get(0);
        String friendId = ids.get(1);

        if (playerId.equals(friendId))
            return new ResponseEntity<>("Player cant be friends with him/herself", HttpStatus.BAD_REQUEST);

        if (playerRepository.findById(ids.get(0)).isPresent()){
            Player player = playerRepository.findById(ids.get(0)).get();
            var friends = player.getFriendIds();
            if (!friends.contains(friendId)){
                friends.add(friendId);
                playerRepository.save(player);
                return new ResponseEntity<>("Friend Added", HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("Players are already friends", HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>("Player not found", HttpStatus.BAD_REQUEST);
        }

    public ResponseEntity<String> RemoveFriend(List<String> ids) {
        String playerId = ids.get(0);
        String friendId = ids.get(1);

        if (playerRepository.findById(playerId).isPresent()){
            Player player = playerRepository.findById(playerId).get();
            var friends = player.getFriendIds();

            if (friends.contains(friendId)){
                friends.remove(friendId);
                playerRepository.save(player);
                return new ResponseEntity<>("Friend Removed", HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("Friend not found", HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>("Player not found", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> UpdatePLayer(Player player) {
        if (playerRepository.findById(player.getId()).isPresent()){
            playerRepository.save(player);
            return new ResponseEntity<>("Player has been updated", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Player not found", HttpStatus.BAD_REQUEST);
    }


    //Delete Services

    public ResponseEntity<String> RemovePlayer(Player player) {
        String id = player.getId();
        if (playerRepository.findById(id).isPresent()){
            playerRepository.delete(player);

            var players = playerRepository.findPlayerByfriendIdsContains(id);
            for (var p:players) {
                p.getFriendIds().remove(id);
                playerRepository.save(p);
            }

            return new ResponseEntity<>("Player has been removed", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Player not found", HttpStatus.BAD_REQUEST);
    }
}

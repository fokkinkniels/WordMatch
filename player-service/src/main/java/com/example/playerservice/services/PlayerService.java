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
            Player friend = playerRepository.findById(ids.get(1)).get();
            var friends = player.getFriendIds();
            var friendsFriends = friend.getFriendIds();

            if (!friends.contains(friendId)){
                friends.add(friendId);
                playerRepository.save(player);
                if(!friendsFriends.contains(playerId)){
                    friendsFriends.add(playerId);
                    playerRepository.save(friend);
                }
                else
                    return new ResponseEntity<>("Players are already friends", HttpStatus.BAD_REQUEST);
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
            Player friend = playerRepository.findById(friendId).get();
            var friends = player.getFriendIds();
            var friendsFriends = friend.getFriendIds();

            if (friends.contains(friendId)){
                friends.remove(friendId);
                playerRepository.save(player);
                if(friendsFriends.contains(playerId)){
                    friendsFriends.remove(playerId);
                    playerRepository.save(friend);
                }
                else
                    return new ResponseEntity<>("Players are already friends", HttpStatus.BAD_REQUEST);
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

    public ResponseEntity<String> RemovePlayer(String id) {

        if (playerRepository.findById(id).isPresent()){
            playerRepository.delete(playerRepository.findById(id).get());

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

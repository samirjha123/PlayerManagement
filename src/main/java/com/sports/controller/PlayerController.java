package com.sports.controller;

import com.sports.constants.Constants;
import com.sports.entity.PlayerHistory;
import com.sports.model.ScoreModel;
import com.sports.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(Constants.PLAYER)
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    /**
     * To register score of player
     * @param score
     * @return
     */
    @PostMapping(Constants.REGISTER)
    public ResponseEntity<ScoreModel> registerScore(@Valid @RequestBody ScoreModel score) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(playerService.registerScore(score));
    }

    /**
     * To get score of a player
     * @param id
     * @return
     */
    @GetMapping(Constants.GET)
    public ResponseEntity<ScoreModel> getScore(@NotNull @RequestParam Long id) {
        ScoreModel score = playerService.getScore(id);
        if(score != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(score);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * To delete score of a player
     * @param id
     * @return
     */
    @DeleteMapping(Constants.DELETE + "/{id}")
    public ResponseEntity<String> deleteScore(@PathVariable Long id) {
        playerService.deleteScore(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * To list scores of players and between date range
     * @param pageable
     * @param players
     * @param time
     * @param after
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseEntity<Page> scorePageable(Pageable pageable, @RequestParam(required = false) String players, @RequestParam(required = false)  String time, @RequestParam(required = false)  boolean after) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(playerService.findScores(pageable, Arrays.asList(players.split(",", -1)), time, after));
    }

    /**
     * To get scores history of a player
     * @param player
     * @return
     */
    @GetMapping(Constants.GET_HISTORY)
    public ResponseEntity<PlayerHistory> getHistory(@NotNull @RequestParam String player) {
        PlayerHistory history = playerService.getHistory(player);
        if(history != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(history);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

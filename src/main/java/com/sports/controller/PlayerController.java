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
    public ResponseEntity<ScoreModel> getScore(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(playerService.getScore(id));
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
     * @param playerNames
     * @param time
     * @param after
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseEntity<Page> blogPageable(Pageable pageable, @RequestParam(required = false) String playerNames, @RequestParam(required = false)  String time, @RequestParam(required = false)  boolean after) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(playerService.findScores(pageable, Arrays.asList(playerNames.split(",", -1)), time, after));
    }

    /**
     * To get scores history of a player
     * @param names
     * @return
     */
    @GetMapping(Constants.GET_HISTORY)
    public ResponseEntity<PlayerHistory> getHistory(@RequestParam List<String> names) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(playerService.getHistory(names));
    }
}

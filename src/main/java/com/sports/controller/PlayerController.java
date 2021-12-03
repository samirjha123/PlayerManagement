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

@RestController
@RequestMapping(Constants.PLAYER)
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping(Constants.REGISTER)
    public ResponseEntity<ScoreModel> registerScore(@Valid @RequestBody ScoreModel score) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(playerService.registerScore(score));
    }

    @GetMapping(Constants.GET)
    public ResponseEntity<ScoreModel> getScore(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(playerService.getScore(id));
    }

    @DeleteMapping(Constants.DELETE + "/{id}")
    public ResponseEntity<String> deleteScore(@PathVariable Long id) {
        playerService.deleteScore(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Page> blogPageable(Pageable pageable, @RequestParam(required = false) String playerName, @RequestParam(required = false)  String time, @RequestParam(required = false)  boolean after) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(playerService.findScores(pageable, playerName, time, after));
    }

    @GetMapping(Constants.GET_HISTORY)
    public ResponseEntity<PlayerHistory> getHistory(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(playerService.getHistory(name));
    }
}

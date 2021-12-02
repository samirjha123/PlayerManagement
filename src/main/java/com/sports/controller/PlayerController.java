package com.sports.controller;

import com.sports.constants.Constants;
import com.sports.model.ScoreModel;
import com.sports.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
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
}

package com.sports.controller;

import com.sports.constants.Constants;
import com.sports.model.ScoreModel;
import com.sports.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.PLAYER)
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping(Constants.REGISTER)
    public ResponseEntity<ScoreModel> registerScore(@RequestBody ScoreModel score) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(playerService.registerScore(score));
    }
}

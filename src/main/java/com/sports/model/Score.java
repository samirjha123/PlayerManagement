package com.sports.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Score {

    private String player;

    private Integer score;

    private String time;
}

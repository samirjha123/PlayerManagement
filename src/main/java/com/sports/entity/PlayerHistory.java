package com.sports.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerHistory {

    private Integer lowScore;

    private Integer highScore;

    private double averageScore;

    private List<Score> scores;
}

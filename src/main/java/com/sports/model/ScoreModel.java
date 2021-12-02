package com.sports.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String player;

    @Min(value = 0L, message = "The value must be positive")
    private Integer score;

    private String time;

}

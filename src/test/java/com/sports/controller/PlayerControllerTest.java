package com.sports.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sports.entity.PlayerHistory;
import com.sports.entity.Score;
import com.sports.model.ScoreModel;
import com.sports.repository.PlayerRepository;
import com.sports.service.PlayerService;
import org.hamcrest.Matchers;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.mockito.Mockito.doNothing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

    @Autowired
    private PlayerController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    Pageable pageable;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testGetScore() throws Exception {

        ScoreModel score = new ScoreModel();
        score.setId(1L);
        score.setScore(25);
        score.setPlayer("samir");
        score.setTime("2021-08-16 20:43:39");

        Mockito.when(playerService.getScore(1L)).thenReturn(score);
        mockMvc.perform(get("/player/get").param("id", "1")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.aMapWithSize(4)))
                .andExpect(jsonPath("$.player", Matchers.equalTo("samir")));
    }

    @Test
    public void testRegisterScore() throws Exception {

        ScoreModel score = new ScoreModel();
        score.setId(2L);
        score.setScore(25);
        score.setPlayer("samir");
        score.setTime("2021-08-16 20:43:39");

        Mockito.when(playerService.registerScore(ArgumentMatchers.any())).thenReturn(score);
        String json = mapper.writeValueAsString(score);
        mockMvc.perform(post("/player/register").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(2)))
                .andExpect(jsonPath("$.player", Matchers.equalTo("samir")));
    }


    @Test
    public void testDeleteScore() throws Exception {
        doNothing().when(playerService).deleteScore(ArgumentMatchers.anyLong());
        this.mockMvc.perform(delete("/player/delete/3"))
                .andExpect(status().isAccepted()).andReturn();
    }

    @Test
    public void testListScore() throws Exception {

        Score score = new Score();
        score.setId(1L);
        score.setScore(25);
        score.setPlayer("samir");
        score.setTime("2021-08-16 20:43:39");

        PlayerHistory history = new PlayerHistory();
        history.setHighScore(100);
        history.setAverageScore(20);
        history.setLowScore(1);
        history.setScores(Arrays.asList(score));

        Mockito.when(playerService.getHistory( "samir")).thenReturn(history);
        mockMvc.perform(get("/player/getHistory").param("player", "samir")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.aMapWithSize(4)))
                .andExpect(jsonPath("$.highScore", Matchers.equalTo(100)));
    }
}

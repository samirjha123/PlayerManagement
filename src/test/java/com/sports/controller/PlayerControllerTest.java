package com.sports.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sports.model.ScoreModel;
import com.sports.repository.PlayerRepository;
import com.sports.service.PlayerService;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    public void testDeleteExample() throws Exception {
        doNothing().when(playerService).deleteScore(ArgumentMatchers.anyLong());
        this.mockMvc.perform(delete("/player/delete/3"))
                .andExpect(status().isAccepted()).andReturn();
    }
}

package com.sports.service;

import com.sports.entity.Score;
import com.sports.mapper.ObjectMapper;
import com.sports.model.ScoreModel;
import com.sports.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    /**
     * save Score
     * @param scoreModel
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ScoreModel registerScore(ScoreModel scoreModel) {
        try {
            Score score = ObjectMapper.OBJECT_MAPPER.scoreModelToScore(scoreModel);
            return ObjectMapper.OBJECT_MAPPER.scoreToScoreModel(playerRepository.save(score));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}


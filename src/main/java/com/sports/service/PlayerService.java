package com.sports.service;

import com.sports.model.Score;
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
     * @param score
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Score registerScore(Score score) {
        try {
            return playerRepository.save(score);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}


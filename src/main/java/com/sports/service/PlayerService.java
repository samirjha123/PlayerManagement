package com.sports.service;

import com.sports.entity.PlayerHistory;
import com.sports.entity.Score;
import com.sports.mapper.ObjectMapper;
import com.sports.model.ScoreModel;
import com.sports.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public ScoreModel getScore(Long id) {
        try {
            return ObjectMapper.OBJECT_MAPPER.scoreToScoreModel(playerRepository.getOne(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteScore(Long id) {
        try {
             playerRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Page<Score> findScores(Pageable pageable, String playerName, String time, boolean after) {
        try {
            if(playerName != null){
                if(time != null){
                    if(after == true){
                        return playerRepository.findAllByPlayerAndTimeAfter(playerName, time, pageable);
                    }
                    return playerRepository.findAllByPlayerAndTimeBefore(playerName, time, pageable);
                }
                return playerRepository.findAllByPlayer(playerName, pageable);
            } else if(time != null){
                if(after == true){
                    return playerRepository.findAllByTimeAfter(time, pageable);
                }
                return playerRepository.findAllByTimeBefore(time, pageable);
            }
            return playerRepository.findAll(pageable);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public PlayerHistory getHistory(String name) {
        try {
            PlayerHistory history = new PlayerHistory();
            List<Score> result = playerRepository.findAllByPlayer(name);
            history.setScores(result);
            double averageScore;
            int lowScore = Integer.MAX_VALUE;
            int highScore = Integer.MIN_VALUE;
            int totalScore = 0;
            for (Score score : result) {
                if (highScore < score.getScore()) {
                    highScore = score.getScore();
                }
                if (lowScore > score.getScore()) {
                    lowScore = score.getScore();
                }
                totalScore = totalScore + score.getScore();
            }
            averageScore = totalScore / result.size();
            history.setLowScore(lowScore);
            history.setHighScore(highScore);
            history.setAverageScore(averageScore);

            return history;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}


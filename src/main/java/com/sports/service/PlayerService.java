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

    /**
     * To get score of a player
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public ScoreModel getScore(Long id) {
        try {
            return ObjectMapper.OBJECT_MAPPER.scoreToScoreModel(playerRepository.getOne(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    /**
     * To delete score of a player
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteScore(Long id) {
        try {
             playerRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    /**
     * To list scores of players and between date range
     * @param pageable
     * @param playerNames
     * @param time
     * @param after
     * @return
     */
    @Transactional(readOnly = true)
    public Page<Score> findScores(Pageable pageable, List<String> playerNames, String time, boolean after) {
        try {
            if(playerNames != null){
                if(time != null){
                    if(after == true){
                        return playerRepository.findByPlayerInAndTimeAfter(playerNames, time, pageable);
                    }
                    return playerRepository.findByPlayerInAndTimeBefore(playerNames, time, pageable);
                }
                return playerRepository.findByPlayerIn(playerNames, pageable);
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

    /**
     * To get scores history of a player
     * @param names
     * @return
     */
    @Transactional(readOnly = true)
    public PlayerHistory getHistory(List<String> names) {
        try {
            PlayerHistory history = new PlayerHistory();
            List<Score> result = playerRepository.findByPlayerIn(names);
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


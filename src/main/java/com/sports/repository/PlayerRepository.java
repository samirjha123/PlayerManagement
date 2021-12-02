package com.sports.repository;

import com.sports.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Score, Long>, JpaSpecificationExecutor<Score> {

    Page<Score> findAllByPlayerAndTimeAfter(String player, String time, Pageable pageable);

    Page<Score> findAllByPlayerAndTimeBefore(String player, String time,  Pageable pageable);

    Page<Score> findAllByPlayer(String player,  Pageable pageable);

    Page<Score> findAllByTimeAfter(String time, Pageable pageable);

    Page<Score> findAllByTimeBefore(String time,  Pageable pageable);

}

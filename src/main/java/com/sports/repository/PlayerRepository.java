package com.sports.repository;

import com.sports.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Score, Long>, JpaSpecificationExecutor<Score> {

    Page<Score> findByPlayerInAndTimeAfter(Collection<String> players, String time, Pageable pageable);

    Page<Score> findByPlayerInAndTimeBefore(Collection<String> player, String time,  Pageable pageable);

    Page<Score> findByPlayerIn(Collection<String> player, Pageable pageable);

    Page<Score> findAllByTimeAfter(String time, Pageable pageable);

    Page<Score> findAllByTimeBefore(String time,  Pageable pageable);

    List<Score> findByPlayerIn(Collection<String> player);
}

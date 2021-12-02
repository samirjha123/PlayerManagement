package com.sports.repository;

import com.sports.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Score, Long>, JpaSpecificationExecutor<Score> {

    Optional<Score> findFirstBySubscriberIdAndMessageId(Long subscriberId, Long messageId);
}

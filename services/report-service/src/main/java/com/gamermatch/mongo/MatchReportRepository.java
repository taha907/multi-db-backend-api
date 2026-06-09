package com.gamermatch.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MatchReportRepository extends MongoRepository<MatchReport, String> {
    List<MatchReport> findByGame(String game);
}

package com.lms.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lms.repository.entity.LogEntity;

@Repository
public interface LogRepository extends MongoRepository<LogEntity, Integer> {
}

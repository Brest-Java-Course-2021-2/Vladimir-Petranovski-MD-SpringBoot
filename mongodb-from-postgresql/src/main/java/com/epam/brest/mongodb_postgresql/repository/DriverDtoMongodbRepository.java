package com.epam.brest.mongodb_postgresql.repository;

import com.epam.brest.mongodb_postgresql.model.DriverDtoMongodb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverDtoMongodbRepository extends MongoRepository<DriverDtoMongodb, Integer> {

}

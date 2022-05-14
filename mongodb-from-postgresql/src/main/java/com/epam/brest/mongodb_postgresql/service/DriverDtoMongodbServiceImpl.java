package com.epam.brest.mongodb_postgresql.service;

import com.epam.brest.mongodb_postgresql.model.DriverDtoMongodb;
import com.epam.brest.mongodb_postgresql.repository.DriverDtoMongodbRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverDtoMongodbServiceImpl implements DriverDtoMongodbService{

    /**
     * Field repository DriverDtoMongodbRepository.
     */

    private final DriverDtoMongodbRepository repository;

    /**
     * Constructor.
     *
     * @param  repository DriverDtoMongodbRepository.
     */

    public DriverDtoMongodbServiceImpl(DriverDtoMongodbRepository repository) {
        this.repository = repository;
    }

    /**
     * Find driver's list.
     *
     * @return driver's list.
     */

    @Transactional(readOnly = true)
    @Override
    public List<DriverDtoMongodb> findAllDriversMongodb() {
        return repository.findAll();
    }
}

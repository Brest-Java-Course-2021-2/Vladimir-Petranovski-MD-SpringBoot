package com.epam.brest.mongodb_postgresql.service;

import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.model.Driver;
import com.epam.brest.mongodb_postgresql.mapper.DriverMongodbFromPostgresqlMapper;
import com.epam.brest.mongodb_postgresql.model.DriverDtoMongodb;
import com.epam.brest.mongodb_postgresql.repository.DriverDtoMongodbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverDtoMongodbServiceImpl implements DriverDtoMongodbService{

    /**
     * Field repository DriverDtoMongodbRepository.
     */

    private DriverDtoMongodbRepository repository;

    /**
     * Field driverDao DriverDao.
     */

    private DriverDao driverDao;

    /**
     * Field driverMongodbFromPostgresqlMapper DriverMongodbFromPostgresqlMapper.
     */

    private DriverMongodbFromPostgresqlMapper driverMongodbFromPostgresqlMapper;

    /**
     * @Constructor without parameters.
     */

    public DriverDtoMongodbServiceImpl() {
    }

    /**
     * Constructor.
     *
     * @param repository DriverDtoMongodbRepository.
     * @param driverDao DriverDao.
     * @param driverMongodbFromPostgresqlMapper DriverMongodbFromPostgresqlMapper.
     */

    @Autowired
    public DriverDtoMongodbServiceImpl(final DriverDtoMongodbRepository repository,
                                       final DriverDao driverDao,
                                       final DriverMongodbFromPostgresqlMapper driverMongodbFromPostgresqlMapper) {
        this.repository = repository;
        this.driverDao=driverDao;
        this.driverMongodbFromPostgresqlMapper = driverMongodbFromPostgresqlMapper;
    }

    /**
     * Find driver's list.
     *
     * @return driver's list.
     */

    @Transactional(readOnly = true)
    @Override
    public List<DriverDtoMongodb> findAllDriversMongodb() {
        repository.deleteAll();
        createDriversCollection();
        return repository.findAll();
    }

    /**
     * Fill collection drivers in MongoDB database.
     *
     */

    protected void createDriversCollection() {
        List<Driver> driverList = driverDao.findAllDrivers();

        driverList.forEach(s -> repository.save(
                driverMongodbFromPostgresqlMapper.getDriverMongodbFromPostgresql(s)));
    }
}

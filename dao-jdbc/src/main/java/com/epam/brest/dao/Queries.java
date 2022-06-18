package com.epam.brest.dao;

import org.springframework.stereotype.Component;

@Component
public class Queries {

    /**
     * Field constant DRIVER_FIND_ALL.
     */

    public static final String DRIVER_FIND_ALL =
            "SELECT d.driver_id, d.name, d.dateStartWork,"
                    + " d.salary FROM driver d ORDER BY d.driver_id";

    /**
     * Field constant DRIVER_FIND_BY_ID.
     */

    public static final String DRIVER_FIND_BY_ID =
            "SELECT * FROM driver WHERE driver_id=:driverId";

    /**
     * Field constant DRIVER_SAVE.
     */

    public static final String DRIVER_SAVE =
            "INSERT INTO driver (name, dateStartWork, salary)"
                    + " VALUES (:driverName, :driverDateStartWork, :driverSalary)";

    /**
     * Field constant DRIVER_UPDATE_BY_ID.
     */

    public static final String DRIVER_UPDATE_BY_ID =
            "UPDATE driver SET name=:driverName, dateStartWork=:driverDateStartWork,"
                    + " salary=:driverSalary WHERE driver_id=:driverId";

    /**
     * Field constant DRIVER_DELETE_BY_ID.
     */

    public static final String DRIVER_DELETE_BY_ID =
            "DELETE FROM driver WHERE driver_id=:driverId";

    /**
     * Field constant DRIVER_CHECK_UNIQUE_NAME.
     */

    public static final String DRIVER_CHECK_UNIQUE_NAME =
            "SELECT COUNT(d.name) FROM driver d WHERE lower(d.name)=lower(:driverName)";

    /**
     * Field constant DRIVER_COUNT.
     */

    public static final String DRIVER_COUNT = "SELECT COUNT(*) FROM driver";

    /**
     * Field constant DRIVER_COUNT_CAR.
     */

    public static final String DRIVER_COUNT_CAR =
            "SELECT d.driver_id as driverId, d.name as driverName,"
                    + " d.dateStartWork as driverDateStartWork, d.salary as driverSalary,"
                    + " COUNT(c.car_id) as countOfCarsAssignedToDriver FROM driver d LEFT JOIN"
                    + " car c ON d.driver_id=c.driver_id GROUP BY d.driver_id ORDER BY d.driver_id";

    /**
     * Field constant DRIVER_FIND_DRIVERS_ON_RANGE_DATE.
     */

    public static final String DRIVER_FIND_DRIVERS_ON_RANGE_DATE =
            "SELECT range.driverId, range.driverName, range.driverDateStartWork, range.driverSalary,"
                    + " range.countOfCarsAssignedToDriver FROM (SELECT d.driver_id as driverId,"
                    + " d.name as driverName, d.dateStartWork as driverDateStartWork,"
                    + " d.salary as driverSalary, COUNT(c.car_id) as countOfCarsAssignedToDriver"
                    + " FROM driver d LEFT JOIN car c ON d.driver_id=c.driver_id GROUP BY"
                    + " d.driver_id) AS range WHERE range.driverDateStartWork BETWEEN"
                    + " :fromDateChoose AND :toDateChoose ORDER BY range.driverId";

    /**
     * Field constant CAR_FIND_ALL.
     */

    public static final String CAR_FIND_ALL =
            "SELECT c.car_id, c.model, c.driver_id FROM car AS c ORDER BY c.car_id";

    /**
     * Field constant CAR_FIND_BY_ID.
     */

    public static final String CAR_FIND_BY_ID =
            "SELECT * FROM car WHERE car_id=:carId";

    /**
     * Field constant CAR_SAVE.
     */

    public static final String CAR_SAVE =
            "INSERT INTO car (model, driver_id) VALUES (:carModel, :driverId)";

    /**
     * Field constant CAR_UPDATE_BY_ID.
     */

    public static final String CAR_UPDATE_BY_ID =
            "UPDATE car SET model=:carModel, driver_id=:driverId WHERE car_id=:carId";

    /**
     * Field constant CAR_DELETE_BY_ID.
     */

    public static final String CAR_DELETE_BY_ID =
            "DELETE FROM car WHERE car_id=:carId";

    /**
     * Field constant CAR_COUNT.
     */

    public static final String CAR_COUNT = "SELECT count(*) FROM car";

    /**
     * Field constant CAR_COUNT.
     */

    public static final String CARS_LIST_ASSIGN_TO_DRIVER =
            "SELECT * FROM car c WHERE c.driver_id = :driverId";

    /**
     * Field constant FIND_MODEL_SPECIFICATION_BY_CAR_MODEL.
     */

    public static final String FIND_MODEL_SPECIFICATION_BY_CAR_MODEL =
            "SELECT ms.model_id AS modelId, ms.model_name AS modelName, ms.description AS description,"
                    + " ms.max_speed AS maxSpeed, ms.carrying_capacity AS carryingCapacity"
                    + " FROM model_specifications AS ms WHERE ms.model_name=:modelName";
}

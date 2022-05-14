package com.epam.brest.mongodb_postgresql.model;

import com.epam.brest.model.Car;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "drivers")
public class DriverDtoMongodb {

    /**
     * @serialField driverId Integer.
     */

    @Id
    private Integer driverId;

    /**
     * @serialField driverName String.
     */

    private String driverName;

    /**
     * @serialField driverDateStartWork Instant.
     */

    private Instant driverDateStartWork;

    /**
     * @serialField driverSalary BigDecimal.
     */

    private BigDecimal driverSalary;

    /**
     * @serialField countOfCarsAssignedToDriver Integer.
     */

    private Integer countOfCarsAssignedToDriver;

    /**
     * @serialField cars Car[].
     */

    private Car[] assignCars;

    /**
     * Constructor without parameters.
     */

    public DriverDtoMongodb() {
    }

    /**
     * Constructor.
     *
     * @param driverId Integer.
     * @param driverName String.
     * @param driverDateStartWork Instant.
     * @param driverSalary BigDecimal.
     * @param countOfCarsAssignedToDriver Integer.
     */

    /**
     * Getter for driverId.
     *
     * @return driverId.
     */

    public Integer getDriverId() {
        return driverId;
    }

    /**
     * Setter for driverId.
     *
     * @param driverId Integer.
     */

    public void setDriverId(final Integer driverId) {
        this.driverId = driverId;
    }

    /**
     * Getter for driverName.
     *
     * @return driverName.
     */

    public String getDriverName() {
        return driverName;
    }

    /**
     * Setter for driverName.
     *
     * @param driverName String.
     */

    public void setDriverName(final String driverName) {
        this.driverName = driverName;
    }

    /**
     * Getter for driverDateStartWork.
     *
     * @return driverDateStartWork.
     */

    public Instant getDriverDateStartWork() {
        return driverDateStartWork;
    }

    /**
     * Setter for driverDateStartWork.
     *
     * @param driverDateStartWork Instant.
     */

    public void setDriverDateStartWork(final Instant driverDateStartWork) {
        this.driverDateStartWork = driverDateStartWork;
    }

    /**
     * Getter for driverSalary.
     *
     * @return driverSalary.
     */

    public BigDecimal getDriverSalary() {
        return driverSalary;
    }

    /**
     * Setter for driverSalary.
     *
     * @param driverSalary BigDecimal.
     */

    public void setDriverSalary(final BigDecimal driverSalary) {
        this.driverSalary = driverSalary;
    }

    /**
     * Getter for countOfCarsAssignedToDriver.
     *
     * @return countOfCarsAssignedToDriver.
     */

    public Integer getCountOfCarsAssignedToDriver() {
        return countOfCarsAssignedToDriver;
    }

    /**
     * Setter for countOfCarsAssignedToDriver.
     *
     * @param countOfCarsAssignedToDriver Integer.
     */

    public void setCountOfCarsAssignedToDriver(
            final Integer countOfCarsAssignedToDriver) {
        this.countOfCarsAssignedToDriver = countOfCarsAssignedToDriver;
    }

}

package com.epam.brest.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Schema(name = "Driver", description = "Driver")
public class Driver {

    /**
     * @serialField driverId Integer.
     */

    @Schema(name = "driverId", description = "driver's unique number", hidden = true)
    private Integer driverId;

    /**
     * @serialField driverName String.
     */

    @Schema(name = "driverName", description = "driver's name", example = "Uladzimir")
    private String driverName;

    /**
     * @serialField driverDateStartWork Instant.
     */

    @Schema(description = "Driver's start work date", example = "2000-01-01T01:01:01.001Z")
    private Instant driverDateStartWork;

    /**
     * @serialField driverSalary BigDecimal.
     */

    @Schema(description = "driver's salary", example = "8000")
    private BigDecimal driverSalary;

    /**
     * Constructor without parameters.
     */

    public Driver() {
    }

    /**
     * Constructor.
     *
     * @param driverName String.
     */

    public Driver(final String driverName) {
        this.driverName = driverName;
    }

    /**
     * Constructor.
     *
     * @param driverName String.
     * @param driverDateStartWork Instant.
     * @param driverSalary BigDecimal.
     */

    public Driver(final String driverName,
                  final Instant driverDateStartWork,
                  final BigDecimal driverSalary) {
        this.driverName = driverName;
        this.driverDateStartWork = driverDateStartWork;
        this.driverSalary = driverSalary;
    }

    /**
     * Constructor.
     *
     * @param driverId Integer.
     * @param driverName String.
     * @param driverDateStartWork Instant.
     * @param driverSalary BigDecimal.
     */

    public Driver(final Integer driverId, final String driverName,
                  final Instant driverDateStartWork,
                  final BigDecimal driverSalary) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverDateStartWork = driverDateStartWork;
        this.driverSalary = driverSalary;
    }

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
     * @return driver.
     */

    public Driver setDriverId(final Integer driverId) {
        this.driverId = driverId;
        return this;
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
     * @return driver.
     */

    public Driver setDriverName(final String driverName) {
        this.driverName = driverName;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(driverId, driver.driverId)
                && Objects.equals(driverName, driver.driverName)
                && Objects.equals(driverDateStartWork,
                driver.driverDateStartWork) && Objects.equals(
                        driverSalary, driver.driverSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId, driverName,
                driverDateStartWork, driverSalary);
    }

    @Override
    public String toString() {
        return "Driver{"
                + "driverId=" + driverId
                + ", driverName='" + driverName + '\''
                + ", driverDateStartWork=" + driverDateStartWork
                + ", driverSalary=" + driverSalary
                + '}';
    }
}

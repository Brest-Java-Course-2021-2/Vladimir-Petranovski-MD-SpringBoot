package com.epam.brest.mongodb_postgresql.model;

import com.epam.brest.model.Car;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

@Document(collection = "drivers")
@Schema(name = "driver-dto-mongodb", description = "DriverDtoMongodb entity")
public class DriverDtoMongodb {

    /**
     * @serialField driverId Integer.
     */

    @Id
    @Schema(hidden = true)
    private Integer driverId;

    /**
     * @serialField driverName String.
     */

    @Schema(name = "driverName", description = "Driver's name", example = "Uladzimir")
    private String driverName;

    /**
     * @serialField driverDateStartWork Instant.
     */

    @Schema(name = "driverDateStartWork", description = "Driver's start work date",
            example = "2000-01-01T01:01:01.001Z")
    private Instant driverDateStartWork;

    /**
     * @serialField driverSalary BigDecimal.
     */

    @Schema(name = "driverSalary", description = "Driver's salary", example = "10000")
    private BigDecimal driverSalary;

    /**
     * @serialField cars Car[].
     */

    @Schema(name = "assignCars", description = "The array of assign car to driver", example = "[\n" +
            "      {\n" +
            "        \"carId\": 1,\n" +
            "        \"carModel\": \"GAZ\",\n" +
            "        \"driverId\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"carId\": 4,\n" +
            "        \"carModel\": \"GIGA\",\n" +
            "        \"driverId\": 1\n" +
            "      }\n" +
            "    ]")
    private Car[] assignCars;

    /**
     * Constructor without parameters.
     */

    public DriverDtoMongodb() {
    }

    /**
     * @Constructor
     *
     * @param driverId Integer.
     * @param driverName String.
     * @param driverDateStartWork Instant.
     * @param driverSalary BigDecimal.
     * @param assignCars Car[].
     */
    public DriverDtoMongodb(final Integer driverId, final String driverName,
                            final Instant driverDateStartWork, final BigDecimal driverSalary,
                            final Car[] assignCars) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverDateStartWork = driverDateStartWork;
        this.driverSalary = driverSalary;
        this.assignCars = assignCars;
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

    public Car[] getAssignCars() {
        return assignCars;
    }

    /**
     * Setter for assignCars.
     *
     * @param assignCars Car[].
     */

    public void setAssignCars(Car[] assignCars) {
        this.assignCars = assignCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverDtoMongodb that = (DriverDtoMongodb) o;
        return Objects.equals(driverId, that.driverId) && Objects.equals(driverName, that.driverName) && Objects.equals(driverDateStartWork, that.driverDateStartWork) && Objects.equals(driverSalary, that.driverSalary) && Arrays.equals(assignCars, that.assignCars);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(driverId, driverName, driverDateStartWork, driverSalary);
        result = 31 * result + Arrays.hashCode(assignCars);
        return result;
    }

    @Override
    public String toString() {
        return "DriverDtoMongodb{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", driverDateStartWork=" + driverDateStartWork +
                ", driverSalary=" + driverSalary +
                ", assignCars=" + Arrays.toString(assignCars) +
                '}';
    }
}

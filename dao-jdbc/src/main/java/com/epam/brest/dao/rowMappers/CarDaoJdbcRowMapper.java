package com.epam.brest.dao.rowMappers;

import com.epam.brest.model.Car;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CarDaoJdbcRowMapper implements RowMapper<Car> {

    /**
     * Get car mapRow.
     *
     * @param resultSet ResultSet.
     * @param i int.
     * @return car Car class.
     * @throws SQLException SQLException class.
     */

    @Override
    public Car mapRow(
            final ResultSet resultSet, final int i) throws SQLException {
        Car car = new Car();
        car.setCarId(resultSet.getInt("car_id"));
        car.setCarModel(resultSet.getString("model"));
        car.setDriverId(resultSet.getInt("driver_id"));
        return car;
    }
}

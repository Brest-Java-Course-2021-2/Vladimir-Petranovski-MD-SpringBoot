package com.epam.brest.dao_api.dto;

import com.epam.brest.model.dto.DriverDto;

import java.util.List;

public interface DriverDtoDao {

    /**
     * Find all drivers Dto.
     *
     * @return list of drivers Dto.
     */

    List<DriverDto> findAllDriversWithCountCars();

    /**
     * Get  list of driver from date to date Dto.
     *
     * @return list of driver from date to date Dto.
     */

    List<DriverDto> chooseDriverOnDateRange(final String fromDateChoose,
                                            final String toDateChoose);
}

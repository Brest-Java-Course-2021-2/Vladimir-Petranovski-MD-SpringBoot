package com.epam.brest.dao.dto;

import com.epam.brest.dao_api.dto.DriverDtoDao;
import com.epam.brest.model.dto.DriverDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.api.TimestampWithTimeZone;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.util.List;

import static com.epam.brest.dao.Queries.DRIVER_COUNT_CAR;
import static com.epam.brest.dao.Queries.DRIVER_FIND_DRIVERS_ON_RANGE_DATE;

@Component
public class DriverDtoDaoJdbcImpl implements DriverDtoDao {

    public static final Logger LOG = LogManager.getLogger(DriverDtoDaoJdbcImpl.class);

    /**
     * Field namedParameterJdbcTemplate.
     */

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Constructor
     *
     * @param namedParameterJdbcTemplate namedParameterJdbcTemplate.
     */

    public DriverDtoDaoJdbcImpl(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find all drivers Dto.
     *
     * @return list of driver Dto.
     */

    @Override
    public List<DriverDto> findAllDriversWithCountCars() {
        LOG.info("Method findAllDriversWithCountCars() of class {} started",
                getClass().getName());
        return namedParameterJdbcTemplate.query(DRIVER_COUNT_CAR,
                BeanPropertyRowMapper.newInstance(DriverDto.class));
    }

    /**
     * Find all drivers Dto from date to date.
     *
     * @return list of driver Dto.
     */

    @Override
    public List<DriverDto> chooseDriverOnDateRange(final String fromDate,
                                                   final String toDate) {
        LOG.info("Method chooseDriverOnDateRange() of class {} started",
                getClass().getName());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
//                .addValue("fromDateChoose", fromDate)
//                .addValue("fromDateChoose", CallableStatement.getTimestamp(fromDate))
                .addValue("fromDateChoose", Timestamp.from(Instant.parse(fromDate)), Types.TIMESTAMP)
                .addValue("toDateChoose", Timestamp.from(Instant.parse(toDate)), Types.TIMESTAMP);

//                .addValue("fromDateChoose", Timestamp.valueOf(fromDate),
//                        Types.TIMESTAMP)
//                        Types.TIMESTAMP_WITH_TIMEZONE)
//                .addValue("toDateChoose", toDate);
//                .addValue("toDateChoose", Timestamp.valueOf(toDate),
//                        Types.TIMESTAMP_WITH_TIMEZONE);
//                        Types.TIMESTAMP);
        return namedParameterJdbcTemplate.query(
                DRIVER_FIND_DRIVERS_ON_RANGE_DATE, sqlParameterSource,
                BeanPropertyRowMapper.newInstance(DriverDto.class));
    }
}

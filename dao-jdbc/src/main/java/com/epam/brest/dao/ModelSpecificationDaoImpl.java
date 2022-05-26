package com.epam.brest.dao;

import com.epam.brest.dao_api.ModelSpecificationDao;
import com.epam.brest.model.ModelSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import static com.epam.brest.dao.Queries.FIND_MODEL_SPECIFICATION_BY_CAR_MODEL;

@Repository
public class ModelSpecificationDaoImpl implements ModelSpecificationDao {

    public static final Logger LOG = LogManager.getLogger(ModelSpecificationDaoImpl.class);

    /**
     * Field namedParameterJdbcTemplate.
     */

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * @Constructor
     *
     * @param namedParameterJdbcTemplate NamedParameterJdbcTemplate.
     */

    public ModelSpecificationDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Getting car's model specification by model name.
     *
     * @param carModel String;
     * @return instance of ModelSpecification.
     */

    @Override
    public ModelSpecification getModelSpecificationByCarModel(String carModel) {
        LOG.info("Method getModelSpecificationByCarModel() of class {} started",
                getClass().getName());

        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource("modelName", carModel);
        return namedParameterJdbcTemplate.queryForObject(FIND_MODEL_SPECIFICATION_BY_CAR_MODEL,
                sqlParameterSource, BeanPropertyRowMapper.newInstance(ModelSpecification.class));
    }
}

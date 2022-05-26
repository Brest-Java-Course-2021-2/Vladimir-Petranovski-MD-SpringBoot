package com.epam.brest.dao;

import com.epam.brest.model.ModelSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import static com.epam.brest.dao.Queries.FIND_MODEL_SPECIFICATION_BY_CAR_MODEL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ModelSpecificationDaoImplTest {

    public static final Logger LOG = LogManager.getLogger(ModelSpecificationDaoImplTest.class);

    @InjectMocks
    private ModelSpecificationDaoImpl modelSpecificationDao;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<SqlParameterSource> sqlParameterSourceArgumentCaptor;

    @Captor
    private ArgumentCaptor<BeanPropertyRowMapper<ModelSpecification>> beanPropertyRowMapperArgumentCaptor;

    private ModelSpecification modelSpecification;


    @BeforeEach
    void setUp() {
        modelSpecification = new ModelSpecification(
                "NISSAN", "Passenger car: made in Japan", 200, 1870);
    }

    @Test
    void getModelSpecificationByCarModel() {
        LOG.info("Method getModelSpecificationByCarModel() of class {} started",
                getClass().getName());
        assertNotNull(namedParameterJdbcTemplate);

        lenient().when(namedParameterJdbcTemplate.queryForObject(anyString(),
                ArgumentMatchers.<SqlParameterSource>any(),
                any(BeanPropertyRowMapper.class))).thenReturn(modelSpecification);

        ModelSpecification modelSpecificationDst = modelSpecificationDao.getModelSpecificationByCarModel(modelSpecification.getModelName());

        verify(namedParameterJdbcTemplate).queryForObject(eq(FIND_MODEL_SPECIFICATION_BY_CAR_MODEL),
                sqlParameterSourceArgumentCaptor.capture(), beanPropertyRowMapperArgumentCaptor.capture());

        BeanPropertyRowMapper<ModelSpecification> rowMapper = beanPropertyRowMapperArgumentCaptor.getValue();
        SqlParameterSource sqlParameterSource = sqlParameterSourceArgumentCaptor.getValue();

        assertNotNull(modelSpecificationDst);
        assertNotNull(rowMapper);
        assertNotNull(sqlParameterSource);
        assertEquals(modelSpecificationDst, modelSpecification);
        LOG.info("ModelSpecification was received after getModelSpecificationByCarModel() {} equals modelSpecification till it {}",
                modelSpecificationDst, modelSpecification);

    }
}
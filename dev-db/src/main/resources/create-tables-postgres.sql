DROP TABLE IF EXISTS model_specifications;

CREATE TABLE model_specifications (
                                      model_id SERIAL NOT NULL PRIMARY KEY,
                                      model_name VARCHAR(20) NOT NULL UNIQUE,
                                      description VARCHAR(255) NOT NULL ,
                                      max_speed INT NOT NULL ,
                                      carrying_capacity INT NOT NULL
);
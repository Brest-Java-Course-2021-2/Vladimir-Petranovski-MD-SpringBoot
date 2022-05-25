DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS driver;
DROP TABLE IF EXISTS model_specifications;

CREATE TABLE driver (
    driver_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE,
    dateStartWork TIMESTAMP NOT NULL,
    salary DECIMAL NOT NULL
);

CREATE TABLE car (
    car_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(20) NOT NULL,
    driver_id INT NOT NULL,
    CONSTRAINT car_driver_fk FOREIGN KEY (driver_id) REFERENCES driver(driver_id)
);

CREATE TABLE model_specifications (
    model_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    model_name VARCHAR(20) NOT NULL ,
    description VARCHAR(255) NOT NULL ,
    max_speed INT NOT NULL ,
    carrying_capacity INT NOT NULL
)
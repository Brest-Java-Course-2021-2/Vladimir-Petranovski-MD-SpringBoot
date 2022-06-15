INSERT INTO `driver` (`driver_id`, `name`, `dateStartWork`, `salary`) VALUES (1, 'VASIA', '1998-10-01T12:02:01.847Z', 500),
                                                                              (2, 'VOVA', '2010-10-11T08:30:30.123Z', 850),
                                                                              (3, 'VITALIY', '2005-04-28T14:44:50.532Z', 650);
INSERT INTO `car` (`car_id`, `model`, `driver_id`) VALUES (1, 'GAZ', 1),
                                                          (2, 'ZIL', 3),
                                                          (3, 'LADA', 3),
                                                          (4, 'GIGA', 1),
                                                          (5, 'URAL', 3);
INSERT INTO `model_specifications` (`model_id`, `model_name`, `description`, `max_speed`, `carrying_capacity`) VALUES (1, 'GAZ', 'Truck: made in Russia', 90, 7500),
                                                                                                                      (2, 'ZIL', 'Truck: made in Russia', 80, 8500),
                                                                                                                      (3, 'LADA', 'Passenger car: made in Russia', 180, 1450),
                                                                                                                      (4, 'GIGA', 'Passenger car: made in Russia', 150, 1200),
                                                                                                                      (5, 'URAL', 'Truck: made in Russia', 80, 10400);

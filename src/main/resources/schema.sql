CREATE TABLE IF NOT EXISTS `car` (
    `id` int AUTO_INCREMENT  PRIMARY KEY,
    `brand_name` varchar(100) NOT NULL,
    `model_name` varchar(100) NOT NULL,
    `reg_no` varchar(20) NOT NULL,
    `car_type` varchar(20) NOT NULL,
    `yr` integer NOT NULL,
    `kms` integer NOT NULL,
    `price` integer NOT NULL
    );

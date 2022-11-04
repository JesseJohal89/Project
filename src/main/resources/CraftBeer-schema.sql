DROP TABLE IF EXISTS craft_beer CASCADE;
CREATE TABLE craft_beer
(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255),
	description VARCHAR(255),
	brewery VARCHAR(255),
	abv FLOAT,
	container VARCHAR(255),
	`size` INT,	
	vegan BOOLEAN,
	gluten_free BOOLEAN,
	price FLOAT
);
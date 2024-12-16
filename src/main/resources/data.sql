CREATE TABLE patient (
id INTEGER NOT NULL AUTO_INCREMENT,
last_name VARCHAR(30) NOT NULL,
first_name VARCHAR(30) NOT NULL,
birthdate VARCHAR(15) NOT NULL,
gender VARCHAR(5) NOT NULL,
address VARCHAR(50),
phoneNumber VARCHAR(20)

);

INSERT INTO patient (lastname, firstname, birthdate, gender, address, phoneNumber)
VALUES
('lastname', 'firstname', '1966-12-31', 'female', 'address', '062545895'),

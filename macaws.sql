DROP DATABASE IF EXISTS macaws;
CREATE DATABASE IF NOT EXISTS macaws;

USE macaws;

CREATE TABLE customer (
  customer_id int NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  PRIMARY KEY (customer_id)
);

CREATE TABLE pilot (
  pilot_id int NOT NULL,
  name varchar(50) NOT NULL,
  PRIMARY KEY (pilot_id)
);

CREATE TABLE route (
  route_id int NOT NULL AUTO_INCREMENT,
  origin char(3) NOT NULL,
  destination char(3) NOT NULL,
  time char(2) NOT NULL,
  PRIMARY KEY (route_id)
);

CREATE TABLE section (
  section_id int NOT NULL,
  section varchar(255) NOT NULL,
  price decimal(10,0) NOT NULL,
  PRIMARY KEY (section_id)
);

CREATE TABLE flight (
  flight_id int NOT NULL,
  depart_date date NOT NULL,
  pilot_id int NOT NULL,
  route_id int NOT NULL,
  PRIMARY KEY (flight_id),
  FOREIGN KEY (route_id) REFERENCES route (route_id),
  FOREIGN KEY (pilot_id) REFERENCES pilot (pilot_id)
);

CREATE TABLE seat (
  seat_id int NOT NULL,
  row int NOT NULL,
  col char(1) NOT NULL,
  section_id int NOT NULL,
  PRIMARY KEY (seat_id),
  KEY FK_2 (section_id),
  FOREIGN KEY (section_id) REFERENCES section (section_id)
);

CREATE TABLE reservation_status (
  status_id int NOT NULL,
  status_name varchar(10) NOT NULL,
  PRIMARY KEY (status_id)
);

CREATE TABLE reservation (
  reservation_id int NOT NULL AUTO_INCREMENT,
  customer_id int NOT NULL,
  status_id int NOT NULL,
  PRIMARY KEY (reservation_id),
  FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
  FOREIGN KEY (status_id) REFERENCES reservation_status (status_id)
);

/* Create a table to store the flight seats and their availability based on reservations made */
/* This table will be used to display the seat map for a flight */
CREATE TABLE flight_seat_availability (
  fs_id int NOT NULL AUTO_INCREMENT,
  flight_id int NOT NULL,
  reservation_id int NOT NULL,
  seat_id int NOT NULL,
  available tinyint(1) NOT NULL, /* 1 = available, 0 = reserved */
  PRIMARY KEY (fs_id),
  FOREIGN KEY (reservation_id) REFERENCES reservation (reservation_id),
  FOREIGN KEY (flight_id) REFERENCES flight (flight_id),
  FOREIGN KEY (seat_id) REFERENCES seat (seat_id)
);

CREATE TABLE reservation_cancel (
  cancel_id int NOT NULL AUTO_INCREMENT,
  reservation_id int NOT NULL,
  fs_id int NOT NULL,
  flight_id int NOT NULL,
  PRIMARY KEY (cancel_id),
  FOREIGN KEY (reservation_id) REFERENCES reservation (reservation_id),
  FOREIGN KEY (fs_id) REFERENCES flight_seat_availability (fs_id),
  FOREIGN KEY (flight_id) REFERENCES flight (flight_id)
);

/* DEMO DATA INSERTS */
INSERT INTO customer (customer_id, first_name, last_name, email) 
VALUES
    (1000,' ',' ',' '), /* Dummy customer for unreserved seats */
    (1001, 'Martin',  'Van Nostrand', 'drvannos@theclinic.org'),
    (1002, 'H.E.',    'Pennypacker',  'wealthy@industrialist.com'),
    (1003, 'Art', 	  'Vandelay',     'latex@vandelayindustries.com'),
    (1004, 'Billy',   'Mumphrey',     'countryboy@lycos.com'),
    (1005, 'Eduardo', 'Corrochio',    'bullfighter@eudora.com'),
    (1006, 'Kel',     'Varnsen',      'go-mets@varnseninc.com');

INSERT INTO pilot (pilot_id, name)
VALUES
	(1,'Pilot 1'),
	(2,'Pilot 2'),
	(3,'Pilot 3');

INSERT INTO route (route_id, origin, destination, time)
VALUES
	(1,'ROA','PHX','AM'),
	(2,'PHX','ROA','AM'),
	(3,'ROA','PHX','PM'),
	(4,'PHX','ROA','PM');

INSERT INTO section (section_id, section, price)
VALUES
	(1,'First',850),
	(2,'Economy',450);

INSERT INTO seat (seat_id, row, col, section_id)
VALUES
	(1,1,'A',1),
	(2,1,'B',1),
	(3,2,'A',1),
	(4,2,'B',1),
	(5,3,'A',2),
	(6,3,'B',2),
	(7,3,'C',2),
	(8,3,'D',2),
	(9,4,'A',2),
	(10,4,'B',2),
	(11,4,'C',2),
	(12,4,'D',2);

INSERT INTO reservation_status (status_id, status_name)
VALUES
    (0,'Open'),
    (1,'Reserved'),
    (2,'Cancelled');

INSERT INTO reservation (reservation_id, customer_id, status_id)
VALUES
  (1,1000,0), /* Dummy reservation for unreserved seats */
  (2,1001,1),
  (3,1002,1),
  (4,1003,1),
  (5,1004,1),
  (6,1005,1),
  (7,1006,1),
  (8,1001,1),
  (9,1006,2);

/* flight_id is year(2022) + month(11) + day(12-18) + route_id(1-4) */
INSERT INTO flight (flight_id, depart_date, pilot_id, route_id)
VALUES
	(202211121,'2022-11-12',1,1),
	(202211122,'2022-11-12',2,2),
	(202211123,'2022-11-12',3,3),
	(202211124,'2022-11-12',1,4),
	(202211131,'2022-11-13',2,1),
	(202211132,'2022-11-13',3,2),
	(202211133,'2022-11-13',1,3),
	(202211134,'2022-11-13',2,4),
  (202211141,'2022-11-12',1,1),
	(202211142,'2022-11-12',2,2),
	(202211143,'2022-11-12',3,3),
	(202211144,'2022-11-12',1,4),
	(202211151,'2022-11-13',2,1),
	(202211152,'2022-11-13',3,2),
	(202211153,'2022-11-13',1,3),
	(202211154,'2022-11-13',2,4),
  (202211161,'2022-11-12',1,1),
	(202211162,'2022-11-12',2,2),
	(202211163,'2022-11-12',3,3),
	(202211164,'2022-11-12',1,4),
	(202211171,'2022-11-13',2,1),
	(202211172,'2022-11-13',3,2),
	(202211173,'2022-11-13',1,3),
	(202211174,'2022-11-13',2,4),
  (202211181,'2022-11-12',1,1),
	(202211182,'2022-11-12',2,2),
	(202211183,'2022-11-12',3,3),
	(202211184,'2022-11-12',1,4);

INSERT INTO flight_seat_availability (flight_id, seat_id, reservation_id, available)
VALUES
    /* First Flight is FULL */
    (202211121,1,2,0),
    (202211121,2,2,0),
    (202211121,3,2,0),
    (202211121,4,2,0),
    (202211121,5,3,0),
    (202211121,6,3,0),
    (202211121,7,3,0),
    (202211121,8,3,0),
    (202211121,9,4,0),
    (202211121,10,4,0),
    (202211121,11,5,0),
    (202211121,12,6,0),
    /* Second Flight only has First Class seats Available */
    (202211122,1,1,1),
    (202211122,2,1,1),
    (202211122,3,1,1),
    (202211122,4,1,1),
    (202211122,5,7,0),
    (202211122,6,7,0),
    (202211122,7,7,0),
    (202211122,8,7,0),
    (202211122,9,9,0),
    (202211122,10,9,0),
    (202211122,11,9,0),
    (202211122,12,8,1), /* available because reservation is cancelled */
    /* Third Flight */
    (202211123,1,1,1),
    (202211123,2,1,1),
    (202211123,3,1,1),
    (202211123,4,1,1),
    (202211123,5,1,1),
    (202211123,6,1,1),
    (202211123,7,1,1),
    (202211123,8,1,1),
    (202211123,9,1,1),
    (202211123,10,1,1),
    (202211123,11,1,1),
    (202211123,12,1,1),
    /* Fourth Flight */
    (202211124,1,1,1),
    (202211124,2,1,1),
    (202211124,3,1,1),
    (202211124,4,1,1),
    (202211124,5,1,1),
    (202211124,6,1,1),
    (202211124,7,1,1),
    (202211124,8,1,1),
    (202211124,9,1,1),
    (202211124,10,1,1),
    (202211124,11,1,1),
    (202211124,12,1,1),
    /* Fifth Flight */
    (202211131,1,1,1),
    (202211131,2,1,1),
    (202211131,3,1,1),
    (202211131,4,1,1),
    (202211131,5,1,1),
    (202211131,6,1,1),
    (202211131,7,1,1),
    (202211131,8,1,1),
    (202211131,9,1,1),
    (202211131,10,1,1),
    (202211131,11,1,1),
    (202211131,12,1,1),
    /* Sixth Flight */
    (202211132,1,1,1),
    (202211132,2,1,1),
    (202211132,3,1,1),
    (202211132,4,1,1),
    (202211132,5,1,1),
    (202211132,6,1,1),
    (202211132,7,1,1),
    (202211132,8,1,1),
    (202211132,9,1,1),
    (202211132,10,1,1),
    (202211132,11,1,1),
    (202211132,12,1,1),
    /* Seventh Flight */
    (202211133,1,1,1),
    (202211133,2,1,1),
    (202211133,3,1,1),
    (202211133,4,1,1),
    (202211133,5,1,1),
    (202211133,6,1,1),
    (202211133,7,1,1),
    (202211133,8,1,1),
    (202211133,9,1,1),
    (202211133,10,1,1),
    (202211133,11,1,1),
    (202211133,12,1,1),
    /* Eighth Flight */
    (202211134,1,1,1),
    (202211134,2,1,1),
    (202211134,3,1,1),
    (202211134,4,1,1),
    (202211134,5,1,1),
    (202211134,6,1,1),
    (202211134,7,1,1),
    (202211134,8,1,1),
    (202211134,9,1,1),
    (202211134,10,1,1),
    (202211134,11,1,1),
    (202211134,12,1,1),
    /* Ninth Flight */
    (202211141,1,1,1),
    (202211141,2,1,1),
    (202211141,3,1,1),
    (202211141,4,1,1),
    (202211141,5,1,1),
    (202211141,6,1,1),
    (202211141,7,1,1),
    (202211141,8,1,1),
    (202211141,9,1,1),
    (202211141,10,1,1),
    (202211141,11,1,1),
    (202211141,12,1,1),
    /* Tenth Flight */
    (202211142,1,1,1),
    (202211142,2,1,1),
    (202211142,3,1,1),
    (202211142,4,1,1),
    (202211142,5,1,1),
    (202211142,6,1,1),
    (202211142,7,1,1),
    (202211142,8,1,1),
    (202211142,9,1,1),
    (202211142,10,1,1),
    (202211142,11,1,1),
    (202211142,12,1,1),
    /* Eleventh Flight */
    (202211143,1,1,1),
    (202211143,2,1,1),
    (202211143,3,1,1),
    (202211143,4,1,1),
    (202211143,5,1,1),
    (202211143,6,1,1),
    (202211143,7,1,1),
    (202211143,8,1,1),
    (202211143,9,1,1),
    (202211143,10,1,1),
    (202211143,11,1,1),
    (202211143,12,1,1),
    /* Twelfth Flight */
    (202211144,1,1,1),
    (202211144,2,1,1),
    (202211144,3,1,1),
    (202211144,4,1,1),
    (202211144,5,1,1),
    (202211144,6,1,1),
    (202211144,7,1,1),
    (202211144,8,1,1),
    (202211144,9,1,1),
    (202211144,10,1,1),
    (202211144,11,1,1),
    (202211144,12,1,1),
    /* Thirteenth Flight */
    (202211151,1,1,1),
    (202211151,2,1,1),
    (202211151,3,1,1),
    (202211151,4,1,1),
    (202211151,5,1,1),
    (202211151,6,1,1),
    (202211151,7,1,1),
    (202211151,8,1,1),
    (202211151,9,1,1),
    (202211151,10,1,1),
    (202211151,11,1,1),
    (202211151,12,1,1),
    /* Fourteenth Flight */
    (202211152,1,1,1),
    (202211152,2,1,1),
    (202211152,3,1,1),
    (202211152,4,1,1),
    (202211152,5,1,1),
    (202211152,6,1,1),
    (202211152,7,1,1),
    (202211152,8,1,1),
    (202211152,9,1,1),
    (202211152,10,1,1),
    (202211152,11,1,1),
    (202211152,12,1,1),
    /* Fifteenth Flight */
    (202211153,1,1,1),
    (202211153,2,1,1),
    (202211153,3,1,1),
    (202211153,4,1,1),
    (202211153,5,1,1),
    (202211153,6,1,1),
    (202211153,7,1,1),
    (202211153,8,1,1),
    (202211153,9,1,1),
    (202211153,10,1,1),
    (202211153,11,1,1),
    (202211153,12,1,1),
    /* Sixteenth Flight */
    (202211154,1,1,1),
    (202211154,2,1,1),
    (202211154,3,1,1),
    (202211154,4,1,1),
    (202211154,5,1,1),
    (202211154,6,1,1),
    (202211154,7,1,1),
    (202211154,8,1,1),
    (202211154,9,1,1),
    (202211154,10,1,1),
    (202211154,11,1,1),
    (202211154,12,1,1),
    /* Seventeenth Flight */
    (202211161,1,1,1),
    (202211161,2,1,1),
    (202211161,3,1,1),
    (202211161,4,1,1),
    (202211161,5,1,1),
    (202211161,6,1,1),
    (202211161,7,1,1),
    (202211161,8,1,1),
    (202211161,9,1,1),
    (202211161,10,1,1),
    (202211161,11,1,1),
    (202211161,12,1,1),
    /* Eighteenth Flight */
    (202211162,1,1,1),
    (202211162,2,1,1),
    (202211162,3,1,1),
    (202211162,4,1,1),
    (202211162,5,1,1),
    (202211162,6,1,1),
    (202211162,7,1,1),
    (202211162,8,1,1),
    (202211162,9,1,1),
    (202211162,10,1,1),
    (202211162,11,1,1),
    (202211162,12,1,1),
    /* Nineteenth Flight */
    (202211163,1,1,1),
    (202211163,2,1,1),
    (202211163,3,1,1),
    (202211163,4,1,1),
    (202211163,5,1,1),
    (202211163,6,1,1),
    (202211163,7,1,1),
    (202211163,8,1,1),
    (202211163,9,1,1),
    (202211163,10,1,1),
    (202211163,11,1,1),
    (202211163,12,1,1),
    /* Twentieth Flight */
    (202211164,1,1,1),
    (202211164,2,1,1),
    (202211164,3,1,1),
    (202211164,4,1,1),
    (202211164,5,1,1),
    (202211164,6,1,1),
    (202211164,7,1,1),
    (202211164,8,1,1),
    (202211164,9,1,1),
    (202211164,10,1,1),
    (202211164,11,1,1),
    (202211164,12,1,1),
    /* Twenty-first Flight */
    (202211171,1,1,1),
    (202211171,2,1,1),
    (202211171,3,1,1),
    (202211171,4,1,1),
    (202211171,5,1,1),
    (202211171,6,1,1),
    (202211171,7,1,1),
    (202211171,8,1,1),
    (202211171,9,1,1),
    (202211171,10,1,1),
    (202211171,11,1,1),
    (202211171,12,1,1),
    /* Twenty-second Flight */
    (202211172,1,1,1),
    (202211172,2,1,1),
    (202211172,3,1,1),
    (202211172,4,1,1),
    (202211172,5,1,1),
    (202211172,6,1,1),
    (202211172,7,1,1),
    (202211172,8,1,1),
    (202211172,9,1,1),
    (202211172,10,1,1),
    (202211172,11,1,1),
    (202211172,12,1,1),
    /* Twenty-third Flight */
    (202211173,1,1,1),
    (202211173,2,1,1),
    (202211173,3,1,1),
    (202211173,4,1,1),
    (202211173,5,1,1),
    (202211173,6,1,1),
    (202211173,7,1,1),
    (202211173,8,1,1),
    (202211173,9,1,1),
    (202211173,10,1,1),
    (202211173,11,1,1),
    (202211173,12,1,1),
    /* Twenty-fourth Flight */
    (202211174,1,1,1),
    (202211174,2,1,1),
    (202211174,3,1,1),
    (202211174,4,1,1),
    (202211174,5,1,1),
    (202211174,6,1,1),
    (202211174,7,1,1),
    (202211174,8,1,1),
    (202211174,9,1,1),
    (202211174,10,1,1),
    (202211174,11,1,1),
    (202211174,12,1,1),
    /* Twenty-fifth Flight */
    (202211181,1,1,1),
    (202211181,2,1,1),
    (202211181,3,1,1),
    (202211181,4,1,1),
    (202211181,5,1,1),
    (202211181,6,1,1),
    (202211181,7,1,1),
    (202211181,8,1,1),
    (202211181,9,1,1),
    (202211181,10,1,1),
    (202211181,11,1,1),
    (202211181,12,1,1),
    /* Twenty-sixth Flight */
    (202211182,1,1,1),
    (202211182,2,1,1),
    (202211182,3,1,1),
    (202211182,4,1,1),
    (202211182,5,1,1),
    (202211182,6,1,1),
    (202211182,7,1,1),
    (202211182,8,1,1),
    (202211182,9,1,1),
    (202211182,10,1,1),
    (202211182,11,1,1),
    (202211182,12,1,1),
    /* Twenty-seventh Flight */
    (202211183,1,1,1),
    (202211183,2,1,1),
    (202211183,3,1,1),
    (202211183,4,1,1),
    (202211183,5,1,1),
    (202211183,6,1,1),
    (202211183,7,1,1),
    (202211183,8,1,1),
    (202211183,9,1,1),
    (202211183,10,1,1),
    (202211183,11,1,1),
    (202211183,12,1,1),
    /* Twenty-eighth Flight */
    (202211184,1,1,1),
    (202211184,2,1,1),
    (202211184,3,1,1),
    (202211184,4,1,1),
    (202211184,5,1,1),
    (202211184,6,1,1),
    (202211184,7,1,1),
    (202211184,8,1,1),
    (202211184,9,1,1),
    (202211184,10,1,1),
    (202211184,11,1,1),
    (202211184,12,1,1);

/* STORED PROCEDURES */

/* Procedure to print pilot's schedule */
DROP PROCEDURE IF EXISTS pilot_schedule;
DELIMITER //
CREATE PROCEDURE pilot_schedule(pilot_id INT)
BEGIN
  SELECT route.origin, route.destination, flight.depart_date, route.time, flight.flight_id, pilot.name, pilot.pilot_id
    FROM flight NATURAL JOIN route NATURAL JOIN pilot
    WHERE pilot_id = pilot_id /* from user input held in a variable */
    ORDER BY pilot_id, flight_id;
END //
DELIMITER ;

/* Procedure to print all the pilots and their flights */
DROP PROCEDURE IF EXISTS all_pilots;
DELIMITER //
CREATE PROCEDURE all_pilots()
BEGIN
  SELECT 
	  flight.flight_id AS 'Flight ID', 
    pilot.name AS 'Pilot Name', 
    route.origin AS 'Origin', 
    route.destination AS 'Destination', 
    flight.depart_date AS 'Departing', 
    route.time AS 'Time'
    FROM pilot NATURAL JOIN flight NATURAL JOIN route
    ORDER BY flight_id, pilot_id;
END //

/* Procedure to print all customers in customer table */
/* Combine first name and last name into one column */
DROP PROCEDURE IF EXISTS ;
DELIMITER //
CREATE PROCEDURE all_customers()
BEGIN
  SELECT 
	  customer_id,
    CONCAT(first_name, ' ', last_name) AS 'Name', 
    email
    FROM customer
    ORDER BY customer_id;
END //
DELIMITER ;

/* Procedure to print ALL Reservations in the system */
/* use the reservation, flight_seat_availabilty, and the customer tables */
/* Exclude reservation id 1 */
DROP PROCEDURE IF EXISTS print_reservations;
DELIMITER //
CREATE PROCEDURE print_reservations()
BEGIN
  SELECT 
    reservation.reservation_id,
    CONCAT(customer.first_name, ' ', customer.last_name) AS 'Name',
    flight_seat_availability.flight_id
    FROM reservation NATURAL JOIN flight_seat_availability NATURAL JOIN customer
    WHERE reservation.reservation_id != 1
    ORDER BY reservation_id;
END //

/* Procedure to print ALL Reservations for a given customer */
DROP PROCEDURE IF EXISTS print_customer_reservations;
DELIMITER //
CREATE PROCEDURE print_customer_reservations(customer_id INT)
BEGIN
  SELECT * FROM reservation
    WHERE customer_id = customer_id; /* from user input held in a variable */
END //
DELIMITER ;

/* Procedure to print ALL customers with reservation data */
DROP PROCEDURE IF EXISTS customer_details;
DELIMITER //
CREATE PROCEDURE customer_details(customer_id INT)
BEGIN
  SELECT customer.customer_id AS 'Customer #',
      CONCAT(customer.first_name, ' ', customer.last_name) AS 'Name',
      customer.email AS 'Email',
      COUNT(reservation_id) AS '# of Reservations',
      SUM(section.price) AS 'Total Cost'
    FROM customer 
      NATURAL JOIN reservation
      NATURAL JOIN seat 
      NATURAL JOIN section 
    WHERE customer.customer_id = customer_id /* from user input held in a variable */
    GROUP BY customer.customer_id;
END //
DELIMITER ;

/******* MULTI-PART BOOK RESERVATION PROCEDURES *******/

/* Procedure to add a reservation to the system */
/* reservation table auto-increments reservation_id */
/* need customer_id and change reservation status_id to 1 */
DROP PROCEDURE IF EXISTS add_reservation;
DELIMITER //
CREATE PROCEDURE add_reservation(customer_id INT)
BEGIN
  INSERT INTO reservation (customer_id, status_id)
    VALUES (customer_id, 1);
END //
DELIMITER ;

/* Procedure to print all seats and their availability for a flight */
/* From flight_seat_availability table */
/* 1 = available, 0 = reserved */
DROP PROCEDURE IF EXISTS print_flight_seats;
DELIMITER //
CREATE PROCEDURE print_flight_seats(flight_id INT)
BEGIN
  SELECT
	  flight_seat_availability.fs_id AS 'Seat ID',
    CONCAT(seat.row, seat.col) AS 'Seat #',
    reservation_id AS 'Reservation ID', 
    CASE WHEN available = 0 THEN 'Reserved' ELSE 'Open' END AS 'Availability',
    section.price AS 'Price'
  FROM seat NATURAL JOIN section NATURAL JOIN flight_seat_availability
  WHERE flight_id = flight_id; /* must get flight_id from user input */
END //
DELIMITER ;

/* Procedure to add a reservation to a seat on a flight and changes the seat availability to 0 (reserved)*/
DROP PROCEDURE IF EXISTS add_seat_to_reservation;
DELIMITER //
CREATE PROCEDURE add_seat_to_reservation(reservation_id INT, fs_id INT)
BEGIN
  UPDATE flight_seat_availability
    SET reservation_id = reservation_id,
    available = 0
    WHERE fs_id = fs_id;
END //
DELIMITER ;

/******* END MULTI-PART BOOK RESERVATION PROCEDURES *******/

/* Procedure to cancel a reservation */
/* UPDATES two tables at once */
/* Must update the reservation table to change the reservation status to 2 (cancelled) */
/* Must update the flight_seat_availability table to remove the 
reservation_id and change the available value to 1 (OPEN) */
DROP PROCEDURE IF EXISTS cancel_reservation;
DELIMITER //
CREATE PROCEDURE cancel_reservation(reservation_id INT, fs_id INT)
BEGIN
  INSERT INTO reservation_cancel (reservation_id, fs_id, flight_id)
    VALUES (reservation_id, fs_id, flight_id);
  UPDATE reservation, flight_seat_availability
    SET reservation.status_id = 2,
      flight_seat_availability.available = 1,
      flight_seat_availability.reservation_id = 1
    WHERE reservation.reservation_id = reservation_id AND 
      flight_seat_availability.reservation_id = reservation_id; /* variables from user input */
END //
DELIMITER ;

/* Procedure to print all cancelled reservations in reservation_cancel table */
/* print flight_id and seat_id too */
/* use the reservation_cancel, flight_seat_availability, and seat tables */
DROP PROCEDURE IF EXISTS print_cancelled_reservations;
DELIMITER //
CREATE PROCEDURE print_cancelled_reservations(reservation_id INT)
BEGIN
  SELECT * FROM reservation_cancel
    WHERE reservation_id = reservation_id;
END //
DELIMITER ;

/* Procedure to ADD a new customer */
DROP PROCEDURE IF EXISTS add_customer;
DELIMITER //
CREATE PROCEDURE add_customer(first_name VARCHAR(255), last_name VARCHAR(255), email VARCHAR(255))
BEGIN
  INSERT INTO customer (first_name, last_name, email)
    VALUES (first_name, last_name, email);
END //
DELIMITER ;

/* Procedure to UPDATE a customer */
DROP PROCEDURE IF EXISTS update_customer;
DELIMITER //
CREATE PROCEDURE update_customer(customer_id INT, first_name VARCHAR(255), last_name VARCHAR(255), email VARCHAR(255))
BEGIN
  UPDATE customer
    SET first_name = first_name, last_name = last_name, email = email
    WHERE customer_id = customer_id;
END //
DELIMITER ;

/* Procedure to CALCULATE total profit of all reservations */
DROP PROCEDURE IF EXISTS calc_total_profit;
DELIMITER //
CREATE PROCEDURE calc_total_profit()
BEGIN
	SELECT SUM(section.price) AS 'Total Gross Profit'
	FROM reservation 
	NATURAL JOIN flight_seat_availability NATURAL JOIN seat NATURAL JOIN section
	WHERE flight_seat_availability.available = 0;
END //
DELIMITER ;

/* Procedure to CALCULATE profit of a specified flight */
DROP PROCEDURE IF EXISTS calc_flight_profit;
DELIMITER //
CREATE PROCEDURE calc_flight_profit(input INT)
BEGIN
	SELECT SUM(section.price) AS 'Total Flight Profit'
    FROM reservation 
	NATURAL JOIN flight_seat_availability NATURAL JOIN seat NATURAL JOIN section
    WHERE flight_seat_availability.available = 0 
    AND flight_seat_availability.flight_id LIKE CONCAT ('%', input, '%');
DELIMITER ;

/* Procedure to display all flight numbers */
DROP PROCEDURE IF EXISTS print_flight_nums;
DELIMITER //
CREATE PROCEDURE print_flight_nums()
BEGIN
	SELECT flight_id
	FROM flight;
END //
DELIMITER ;
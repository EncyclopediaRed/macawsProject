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
  seat_id int NOT NULL AUTO_INCREMENT,
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
  flight_id int NOT NULL,
  seat_id int NOT NULL,
  customer_id int NOT NULL,
  status_id int NOT NULL,
  PRIMARY KEY (reservation_id),
  FOREIGN KEY (flight_id) REFERENCES flight (flight_id),
  FOREIGN KEY (seat_id) REFERENCES seat (seat_id),
  FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
  FOREIGN KEY (status_id) REFERENCES reservation_status (status_id)
);

/* Create a table to store the flight seats and their availability based on reservations made */
/* This table will be used to display the seat map for a flight */
CREATE TABLE flight_seat_reservation (
  flight_id int NOT NULL,
  seat_id int NOT NULL,
  available tinyint(1) NOT NULL,
  PRIMARY KEY (flight_id, seat_id),
  FOREIGN KEY (flight_id) REFERENCES flight (flight_id),
  FOREIGN KEY (seat_id) REFERENCES seat (seat_id)
);

/* DEMO DATA INSERTS */
INSERT INTO customer (customer_id, first_name, last_name, email) 
VALUES
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
    (2,'Canceled');

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

INSERT INTO reservation (reservation_id, flight_id, seat_id, customer_id, status_id)
VALUES
    /* First Flight is FULL */
    (1,202211121,1,1001,1),
    (2,202211121,2,1001,1),
    (3,202211121,3,1001,1),
    (4,202211121,4,1001,1),
    (5,202211121,5,1002,1),
    (6,202211121,6,1002,1),
    (7,202211121,7,1003,1),
    (8,202211121,8,1003,1),
    (9,202211121,9,1003,1),
    (10,202211121,10,1003,1),
    (11,202211121,11,1004,1),
    (12,202211121,12,1004,1),
    /* Second Flight only has First Class seats Available */
    (13,202211122,5,1006,1),
    (14,202211122,6,1006,1),
    (15,202211122,7,1006,1),
    (16,202211122,8,1006,1),
    (17,202211122,9,1004,1),
    (18,202211122,10,1004,1),
    (19,202211122,11,1005,1),
    (20,202211122,12,1005,2), /* Canceled */
    /* Third Flight has one seat taken */
    (21,202211123,1,1003,1);

/* STORED PROCEDURES */

/* Procedure to print all seats on a flight and their status 
* |  Flight ID  |  Seat  |  Status   |  Customer  |
* |  202211122  |  1A    |  Reserved |  Eduardo Corrochio   |
* 
* Concatenate the row and column to get the seat number
* If the seat is not reserved, print OPEN for the customer name
*/
DROP PROCEDURE IF EXISTS print_flight_seats;
DELIMITER //
CREATE PROCEDURE print_flight_seats(IN flight_id INT)
BEGIN
    SELECT seat.seat_id, seat.row, seat.col, section.section, section.price, reservation_status.status_name
    FROM seat
    INNER JOIN section ON seat.section_id = section.section_id
    LEFT JOIN reservation ON seat.seat_id = reservation.seat_id
    LEFT JOIN reservation_status ON reservation.status_id = reservation_status.status_id
    WHERE seat.seat_id IN (SELECT seat_id FROM reservation WHERE flight_id = flight_id)
    ORDER BY seat.row, seat.col;
END //
DELIMITER ;


/* Procedure to print pilot's schedule */
DROP PROCEDURE IF EXISTS pilot_schedule;
DELIMITER //
CREATE PROCEDURE pilot_schedule(pilot_id INT)
BEGIN
  SELECT route.origin, route.destination, flight.depart_date, route.time, flight.flight_id, pilot.name, pilot.pilot_id
    FROM flight NATURAL JOIN route NATURAL JOIN pilot
    WHERE pilot_id = pilot_id
    ORDER BY pilot_id, flight_id;
END //
DELIMITER ;

/* Procedure to print ALL Reservations in the system */
DROP PROCEDURE IF EXISTS print_reservations;
DELIMITER //
CREATE PROCEDURE print_reservations()
BEGIN
  SELECT * FROM reservation;
END //

/* Procedure to print ALL Reservations for a given flight */
DROP PROCEDURE IF EXISTS print_flight_reservations;
DELIMITER //
CREATE PROCEDURE print_flight_reservations(flight_id INT)
BEGIN
  SELECT * FROM reservation
    WHERE flight_id = flight_id;
END //
DELIMITER ;

/* Procedure to print ALL Reservations for a given customer */
DROP PROCEDURE IF EXISTS print_customer_reservations;
DELIMITER //
CREATE PROCEDURE print_customer_reservations(customer_id INT)
BEGIN
  SELECT * FROM reservation
    WHERE customer_id = customer_id;
END //
DELIMITER ;

/* Procedure to print ALL customers */
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
    WHERE customer.customer_id = customer_id
    GROUP BY customer.customer_id;
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

/* Procedure to ADD a new reservation and set the seats selected to reserved*/
DROP PROCEDURE IF EXISTS add_reservation;
DELIMITER //
CREATE PROCEDURE add_reservation(flight_id INT, seat_id INT, customer_id INT)
BEGIN
  INSERT INTO reservation (flight_id, seat_id, customer_id, status_id)
    VALUES (flight_id, seat_id, customer_id, 0);
  UPDATE seat
    SET status_id = 1
    WHERE seat_id = seat_id;
END //
DELIMITER ;

/* Procedure to CANCEL a reservation */
DROP PROCEDURE IF EXISTS cancel_reservation;
DELIMITER //
CREATE PROCEDURE cancel_reservation(reservation_id INT)
BEGIN
  UPDATE reservation
    SET status_id = 2
    WHERE reservation_id = reservation_id;
END //
DELIMITER ;

/* Procedure to UPDATE a reservation */
DROP PROCEDURE IF EXISTS update_reservation;
DELIMITER //
CREATE PROCEDURE update_reservation(reservation_id INT, flight_id INT, seat_id INT, customer_id INT)
BEGIN
  UPDATE reservation
    SET flight_id = flight_id, seat_id = seat_id, customer_id = customer_id
    WHERE reservation_id = reservation_id;
END //
DELIMITER ;
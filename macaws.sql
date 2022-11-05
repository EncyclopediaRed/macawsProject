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

CREATE TABLE ticket (
  ticket_id int NOT NULL AUTO_INCREMENT,
  flight_id int NOT NULL,
  seat_id int NOT NULL,
  customer_id int NOT NULL,
  PRIMARY KEY (ticket_id),
  FOREIGN KEY (flight_id) REFERENCES flight (flight_id),
  FOREIGN KEY (seat_id) REFERENCES seat (seat_id),
  FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

CREATE TABLE reservation_status (
  status_id int NOT NULL,
  status_name varchar(10) NOT NULL,
  PRIMARY KEY (status_id)
);

CREATE TABLE reservation (
  reservation_id int NOT NULL AUTO_INCREMENT,
  ticket_id int NOT NULL,
  status_id int NOT NULL,
  PRIMARY KEY (reservation_id),
  FOREIGN KEY (ticket_id) REFERENCES ticket (ticket_id),
  FOREIGN KEY (status_id) REFERENCES reservation_status (status_id)
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

INSERT INTO ticket (ticket_id, flight_id, seat_id, customer_id)
VALUES
    (1,202211121,1,1001),
    (2,202211121,2,1002),
    (3,202211121,3,1003),
    (4,202211121,4,1004),
    (5,202211121,5,1005),
    (6,202211121,6,1006),
    (7,202211122,7,1001),
    (8,202211122,8,1002),
    (9,202211122,9,1003),
    (10,202211122,10,1004);

INSERT INTO reservation (reservation_id, ticket_id, status_id)
VALUES
    (1,1,1),
    (2,2,1),
    (3,3,1),
    (4,4,1),
    (5,5,1),
    (6,6,1),
    (7,7,1),
    (8,8,1),
    (9,9,1),
    (10,10,2);

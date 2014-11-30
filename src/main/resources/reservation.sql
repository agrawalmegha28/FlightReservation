create table Reservation(
id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
Flight_id int,
Passenger_id int,
Code varchar(32),
Foreign key(Flight_id) references Flight(id),
Foreign key(Passenger_id) references Passenger(id));
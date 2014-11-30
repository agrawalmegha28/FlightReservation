create table Passenger(
id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
FName varchar(32) NOT NULL,
MName varchar(32),
LName varchar(32) NOT NULL,
Gender varchar(10) NOT NULL,
Dob date NOT NULL);

INSERT INTO `Passenger` (`id`,`FName`,`MName`,`LName`,`Gender`,`Dob`) VALUES (2,'Megha',NULL,'Agrawal','F','2014-11-26');
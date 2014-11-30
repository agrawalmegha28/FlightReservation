create table Flight(
id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
FNo int,
FName varchar(32),
Ffrom varchar(32),
Fto varchar(32),
Depart datetime,
Price float(20)); 


INSERT INTO `Flight` (`id`,`FNo`,`FName`,`Ffrom`,`Fto`,`Depart`,`Price`) VALUES (1,200,'Lufthansa','USA','India','2014-11-26 20:02:33',1000);
INSERT INTO `Flight` (`id`,`FNo`,`FName`,`Ffrom`,`Fto`,`Depart`,`Price`) VALUES (2,200,'Lufthansa','USA','India','2014-11-26 20:03:37',1000);
INSERT INTO `Flight` (`id`,`FNo`,`FName`,`Ffrom`,`Fto`,`Depart`,`Price`) VALUES (3,400,'Lufthansa','France','USA','2014-11-26 20:27:53',5000);
INSERT INTO `Flight` (`id`,`FNo`,`FName`,`Ffrom`,`Fto`,`Depart`,`Price`) VALUES (4,200,'Lufthansa','USA','India','2014-11-26 20:27:54',1000);
INSERT INTO `Flight` (`id`,`FNo`,`FName`,`Ffrom`,`Fto`,`Depart`,`Price`) VALUES (5,400,'Lufthansa','France','USA','2014-11-26 20:29:19',5000);
INSERT INTO `Flight` (`id`,`FNo`,`FName`,`Ffrom`,`Fto`,`Depart`,`Price`) VALUES (6,400,'Lufthansa','France','USA','2014-11-26 20:29:59',5000);
INSERT INTO `Flight` (`id`,`FNo`,`FName`,`Ffrom`,`Fto`,`Depart`,`Price`) VALUES (7,400,'Lufthansa','France','USA','2014-11-26 20:32:21',5000);
INSERT INTO `Flight` (`id`,`FNo`,`FName`,`Ffrom`,`Fto`,`Depart`,`Price`) VALUES (8,451,'Emirates','451','India','2014-11-26 21:42:47',5000);

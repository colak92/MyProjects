INSERT INTO `hibernate_sequence` VALUES (75);

INSERT INTO `cities` VALUES (1,21000,'Novi Sad'),(2,22320,'Indjija'),(3,32200,'Novi Pazar'),(6,34000,'Kragujevac'),(7,11000,'Beograd'),(8,36000,'Kraljevo');

INSERT INTO `titles` VALUES (1,'Assistant Professor'),(2,'Associate Professor'),(3,'Professor'),(4,'Instructor');

INSERT INTO `students` VALUES (1,'rs51',2021,'Gojko','Gojkovic',1111111111111,'gojko@gmail.com','Novosadska 1A',1,1),(2,'rs22',2020,'Bozo','Bozovic',2222222222222,'boza@yahoo.com','Vojvode Stepe 3A',2,7),(3,'rs31',2019,'Svetlana','Sokanovic',3333333333333,'svetlana@yahoo.com','Kralja Petra I',3,6),(28,'rs10',2018,'Aleksa','Aleksic',4444444444444,'aleksa@gmail.com','Cara Dušana 9',4,3);

INSERT INTO `subjects` VALUES (1,'Mathematics','Mathematics is used in science for modeling phenomena.',5,1,'Summer'),(2,'Programming','Computer programming is the process of performing a particular computation.',4,1,'Winter'),(3,'Psychology','Psychology is the scientific study of mind and behavior.',2,2,'Winter'),(4,'Geology','Geology is the study of the structure, evolution and dynamics of the Earth and its natural mineral and energy resources.',2,4,'Summer'),(6,'Biology','Biology is a branch of science that deals with living organisms and their vital processes.',3,3,'Summer');

INSERT INTO `professors` VALUES (4,'Marko','Markovic','marko@gmail.com','Vojvode Stepe 3A','+3816612133322','2022-10-06 11:38:00',7,3,6),(5,'Petar','Petrovic','petar@gmail.com','Novosadska 6B','+3816012345678','2022-10-07 11:40:00',1,1,1),(7,'Zarko','Zarkovic','zarko@gmail.com','Dimitrija Tucovica BB','00381987333222','2022-10-01 11:43:00',6,4,4);

INSERT INTO `examperiods` VALUES (1,'October exam period','2022-10-01 15:37:00','2022-10-30 15:37:00',1),(8,'September exam period','2022-09-01 11:44:00','2022-09-30 11:44:00',0),(14,'August exam period','2022-08-01 16:16:00','2022-08-30 16:16:00',0),(42,'Jun exam period','2022-06-01 02:00:00','2022-06-30 02:00:00',0),(62,'Novembar Exam','2022-11-01 19:08:00','2022-11-30 19:08:00',0);

INSERT INTO `exams` VALUES (57,'Exam test 1','2022-10-11 18:52:00',10,1,1,1,4,1,1),(69,'Exam test 2','2022-10-12 11:40:00',9,1,1,1,4,2,28),(73,'Exam test 3','2022-10-12 12:20:00',5,0,0,1,4,2,1);

INSERT INTO `examregs` VALUES (58,'This is regstration.',57,1,1,1),(70,'This ispit',69,1,28,2),(74,NULL,73,1,1,2);
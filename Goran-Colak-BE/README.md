#Java 11 + Spring Boot 2 + Angular 14 + MySQL (Student Service)

## 1. Create Database in MySQL database

- Create database gorandb in MySQL server.
- For some reason I must first create database gorandb without data but I want to create new database if not exists in mysql server.
- After that SpringBoot execute schema.sql and re-create database gorandb.
- Next SpringBoot execute data.sql and insert data.

CREATE DATABASE  IF NOT EXISTS `gorandb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gorandb`;

## 2. Run Spring Boot application (Goran-Colak-BE)

- Open command promnt (cmd) and find location of Goran-Colak-BE
- From that location execute (mvn spring-boot:run)
- Now backend project is running ...

```
mvn spring-boot:run
```
The Spring Boot Server will export API at port `4200`.

## 3. Run Angular Client (Goran-Colak-FE)

- Create NewFolder (ex. AngularProjects) somewhere and copy Goran-Colak-FE into that folder
- Open command promnt (cmd) and find location of Goran-Colak-FE\
- Enter command (code .) to open project data in Visual Studio Code
- Open terminal and execute (cd Goran-Colak-FE) and execute (npm install)
- Next in terminal execute (ng serve --port 4200) to run Goran-Colak-FE project
- Now frontend project is running ... 

```
npm install
ng serve --port 4200


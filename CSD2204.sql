-- DDL

CREATE database mytestdb;

USE mytestdb;

CREATE TABLE student
(
	sid INT(5),
	snm VARCHAR(25),
	gender VARCHAR(10),
	age INT(3)
);

CREATE TABLE test_reserved_word
(
	`use` VARCHAR(10);
);

-- show the complete form of create table
SHOW CREATE TABLE products\G

INSERT INTO student VALUES (
1,
'denis',
'male',
29
);

-- specifing the columns names, it is possible to change the order of fields
INSERT INTO student (sid, snm, gender, age) VALUES (
	3,
	'deepak',
	'male',
	27
);

-- Just for mysql
INSERT student VALUES (
2,
'gislaine',
'female',
30
);

INSERT student VALUES (4, 'thomas', 'male', 31);
INSERT student VALUES (5, 'victoria', 'female', 11);
INSERT student VALUES (6, 'arthur', 'male', 3);
INSERT student VALUES (7, 'isaac', 'male', 75);
INSERT student VALUES (8, 'sara', 'female', 40);
INSERT student VALUES (9, 'matheus', 'male', 15);

-- use of ALTER and clauses
ALTER TABLE student ADD marks int(2);
ALTER TABLE student ADD city varchar(50) AFTER gender;

-- UPDATE
UPDATE student SET city='Toronto', marks=100 WHERE sid IN (1,5,9);
UPDATE student SET city='Delhi', marks=80 WHERE sid = 3;
UPDATE student SET city='Mississauga', marks=100 WHERE snm='isaac' AND gender='male';
UPDATE student SET city='Montreal', marks=55 WHERE gender='female' AND city IS NULL;
UPDATE student SET marks=75 where marks is null;


-- DML

-- filtering
SELECT * FROM student;

SELECT * FROM student where sid = 1;

SELECT * FROM student where sid >= 3;

SELECT * FROM student where sid != 5;

SELECT * FROM student where sid <> 5;

SELECT * FROM student WHERE gender = 'male' AND age > 15;

SELECT * FROM student LIMIT 3;

SELECT * FROM student LIMIT 5,9;

-- show the structure of a object
DESC student;

-- data matching using like
-- end
SELECT * FROM student WHERE snm LIKE 'ab%';

-- begin
SELECT * FROM student WHERE snm LIKE '%ab';

-- using coringa char
SELECT * FROM student WHERE snm LIKE '_b%';

-- pay attetion for all clauses, in this example the second condition is false
SELECT * FROM student WHERE snm LIKE '_b%' or '_a%';

-- now it is true
SELECT * FROM student WHERE snm LIKE '_b%' or snm LIKE '_a%';

-- using between, in and any
SELECT * FROM student WHERE age BETWEEN 20 AND 30;
SELECT * FROM student WHERE age IN(20,30,27);

-- use of AS
select sid as StudentId FROM student;
select sid StudentId FROM student;
select sid as `Student Id` FROM student;

-- Useful Functions
SELECT VERSION();
SELECT USER();
SELECT CURDATE();
SELECT CURRENT_DATE();
SELECT NOW();
SELECT SYSDATE();
SELECT CURTIME();
SELECT CURRENT_TIME();
SELECT CURRENT_TIMESTAMP();
SELECT CONCAT(snm, ' living in ', city) FROM student;
SELECT FORMAT(1233344.33342, 2, 'br');

CREATE TABLE Manufacturers (
	Code INTEGER(3),
	Name VARCHAR(50),
	PRIMARY KEY (Code)
);

-- or

CREATE TABLE Manufacturers (
	Code integer(3) PRIMARY KEY,
	Name VARCHAR(50) NOT NULL
);

CREATE TABLE Products (
 Code INTEGER,
 Name VARCHAR(255) NOT NULL ,
 Price DECIMAL NOT NULL ,
 Manufacturer INTEGER NOT NULL,
 PRIMARY KEY (Code),
 FOREIGN KEY (Manufacturer) REFERENCES Manufacturers(Code)
) ;

-- or

CREATE TABLE Products (
	Code integer(3) PRIMARY KEY,
	Name VARCHAR(50) NOT NULL,
	Price DECIMAL NOT NULL,
	Manufacturer integer(3));
	
ALTER TABLE Products 
ADD CONSTRAINT products_ibfk_1_denis 
FOREIGN KEY (Manufacturer) 
REFERENCES Manufacturers(Code);

INSERT INTO Manufacturers VALUES (1, 'Nike');
INSERT INTO Products VALUES (1, 'Nike Run Sneaker', 10.00, 1);

INSERT INTO Manufacturers(Code,Name) VALUES(1,'Sony');
INSERT INTO Manufacturers(Code,Name) VALUES(2,'Creative Labs');
INSERT INTO Manufacturers(Code,Name) VALUES(3,'Hewlett-Packard');
INSERT INTO Manufacturers(Code,Name) VALUES(4,'Iomega');
INSERT INTO Manufacturers(Code,Name) VALUES(5,'Fujitsu');
INSERT INTO Manufacturers(Code,Name) VALUES(6,'Winchester');

INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(1,'Hard drive',240,5);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(2,'Memory',120,6);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(3,'ZIP drive',150,4);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(4,'Floppy disk',5,6);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(5,'Monitor',240,1);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(6,'DVD drive',180,2);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(7,'CD drive',90,2);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(8,'Printer',270,3);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(9,'Toner cartridge',66,3);
INSERT INTO Products(Code,Name,Price,Manufacturer) VALUES(10,'DVD burner',180,2);

-- Group by
SELECT name, COUNT(name) FROM products GROUP BY name;
SELECT manufacturer, COUNT(*), AVG(price) FROM products GROUP BY manufacturer;

-- Having
SELECT manufacturer, COUNT(*), AVG(price) FROM products GROUP BY manufacturer HAVING AVG(price) > 200;
SELECT manufacturer, COUNT(*), AVG(price) FROM products GROUP BY manufacturer WHERE HAVING AVG(price) > 200 AND manufacturer = 1;
SELECT name, manufacturer, COUNT(*), AVG(price) FROM products GROUP BY manufacturer HAVING AVG(price) > 200 AND name LIKE '%drive%';

-- Select into
SELECT name FROM products WHERE code=1 INTO @a;
SELECT @foo := name FROM products WHERE code =1;
SELECT * FROM products INTO outfile 'C:/Users/711561/Downloads/output.sql';

-- Union must have the same number of columns
SELECT name FROM products UNION SELECT code FROM manufacturers;

-- Subselect
SELECT name FROM manufacturers WHERE code IN (SELECT manufacturer FROM products WHERE price > 200);
SELECT name FROM manufacturers WHERE code IN (SELECT manufacturer FROM products WHERE name LIKE '%drive%');



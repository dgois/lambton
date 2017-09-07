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

SELECT * FROM student;

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

-- filtering
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

-- use of ALTER and clauses
ALTER TABLE student ADD marks int(2);
ALTER TABLE student ADD city varchar(50) AFTER gender;

-- use of AS
select sid as StudentId FROM student;
select sid StudentId FROM student;
select sid as `Student Id` FROM student;

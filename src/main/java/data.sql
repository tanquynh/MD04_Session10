use demosession10;
create table classroom(
    id int primary key auto_increment,
    name varchar(100) not null ,
    quantity int default 0
);
create table Student(
    studentId int primary key auto_increment,
    studentName varchar(100),
    birthday DATE,
    classroomId int,
    FOREIGN KEY (classroomId) REFERENCES classroom(id)
);

INSERT INTO classroom(id, name) VALUES (1, 'Lop Toan');
INSERT INTO classroom(id, name) VALUES (2, 'Lop Van');
INSERT INTO classroom(id, name) VALUES (3, 'Lop Ngoai Ngu');
select classroom.id, classroom.name, count(student.classroomId) 'quantity' from classroom left join student on classroom.id = student.classroomId group by classroom.id order by classroom.id;
select  * from classroom;
# Tim kiem tat ca lop hoc
DELIMITER //

CREATE PROCEDURE getAll()
BEGIN
    SELECT  Student.studentId as 'Student ID', Student.studentName as 'Name', Student.birthday as 'birthday',classroom.name as  className from student  join classroom on classroom.id = student.classroomId group by student.classroomId , Student.studentId , Student.studentName, Student.birthday order by student.classroomId;
END //


DELIMITER ;

INSERT INTO Student(studentName,birthday,classroomId) VALUES ('Quynh',  '1994-05-16','1');


DELIMITER //

# update srudent
DELIMITER //

CREATE PROCEDURE UpdateStudent(
    IN p_studentId INT,
    IN p_studentName VARCHAR(100),
    IN p_birthday DATE,
    IN p_classroomId INT
)
BEGIN
    UPDATE Student
    SET
        studentName = p_studentName,
        birthday = p_birthday,
        classroomId = p_classroomId
    WHERE studentId = p_studentId;
END //

DELIMITER ;

# getById
DELIMITER //

CREATE PROCEDURE getById(
    IN p_studentId INT
)
BEGIN
    SELECT
        Student.studentId AS 'Student ID',
        Student.studentName AS 'Name',
        Student.birthday AS 'Birthday',
        classroom.id AS 'Class ID',
        classroom.name AS 'Class'
    FROM
        student
            JOIN
        classroom ON classroom.id = student.classroomId
    WHERE
            student.studentId = p_studentId
    ORDER BY
        student.classroomId;
END //

DELIMITER ;




# add student
DELIMITER //

CREATE PROCEDURE addNewStudent(
    IN p_studentName varchar(100),
    IN p_birthday Date,
    IN p_classroomId int
)
BEGIN
    INSERT INTO Student (studentName, birthday, classroomId)
    VALUES (p_studentName, p_birthday, p_classroomId);
END //

DELIMITER ;

#  xoa
DELIMITER //

CREATE PROCEDURE deleteStudent(
    IN student_id INT
)
BEGIN
    DELETE FROM Student
    WHERE studentId = student_id;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE FindStudentsByKeyword(
    IN p_keyword VARCHAR(100)
)
BEGIN
    SELECT
        Student.studentId AS 'Student ID',
        Student.studentName AS 'Name',
        Student.birthday AS 'Birthday',
        Student.classroomId AS 'Class ID',
        classroom.name AS 'Class'
    FROM
        student
            JOIN
        classroom ON classroom.id = student.classroomId
    WHERE
            Student.studentName LIKE CONCAT('%', p_keyword, '%')
       OR classroom.name LIKE CONCAT('%', p_keyword, '%')
    ORDER BY
        Student.studentId;
END //

DELIMITER ;

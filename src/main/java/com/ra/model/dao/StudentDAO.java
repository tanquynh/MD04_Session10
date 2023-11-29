package com.ra.model.dao;

import com.ra.dto.StudentDTO;
import com.ra.model.entity.Student;
import com.ra.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentDAO implements IGenericDAO<StudentDTO, Integer> {
    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> studentList = new ArrayList<>();
        Connection connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{call getAll()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                StudentDTO student = new StudentDTO();
                student.setId(resultSet.getInt("Student ID"));
                student.setName(resultSet.getString("name"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setClassName(resultSet.getString("className"));
                studentList.add(student);
            }
        } catch (SQLException e) {
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return studentList;
    }

    @Override
    public boolean save(StudentDTO studentDTO) {
        Connection connection = ConnectionDB.openConnection();
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setBirthday(studentDTO.getBirthday());
        student.setClassId(studentDTO.getClassId());
        try {
            CallableStatement callableStatement = connection.prepareCall("{call addNewStudent(?, ?, ?)}");
            callableStatement.setString(1, student.getName());
            callableStatement.setDate(2, student.getBirthday());
            callableStatement.setInt(3, student.getClassId());
            int check = callableStatement.executeUpdate();
            if (check > 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public StudentDTO getById(Integer studentId) {
        StudentDTO studentDTO = new StudentDTO();
        Connection connection = ConnectionDB.openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall("{call getById(?)}");
            callableStatement.setInt(1, studentId);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                studentDTO.setId(resultSet.getInt("Student ID"));
                studentDTO.setName(resultSet.getString("Name"));
                studentDTO.setBirthday(resultSet.getDate("Birthday"));
                studentDTO.setClassId(resultSet.getInt("Class ID"));
                studentDTO.setClassName(resultSet.getString("Class"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        }
        return studentDTO;
    }

    @Override
    public boolean update(StudentDTO studentDTO, Integer studentId) {
        Connection connection = ConnectionDB.openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall("{call UpdateStudent(?, ? , ?, ?)}");
            callableStatement.setInt(1, studentId);
            callableStatement.setString(2, studentDTO.getName());
            callableStatement.setDate(3, studentDTO.getBirthday());
            callableStatement.setInt(4, studentDTO.getClassId());
            int check = callableStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;

    }

    @Override
    public boolean delete(Integer studentId) {
        Connection connection = ConnectionDB.openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall("{call deleteStudent(?)}");
            callableStatement.setInt(1, studentId);
            int check = callableStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    public List<StudentDTO> getSortedStudentDTOList() {
        List<StudentDTO> sortedStudentDTOList = getAll();
        Collections.sort(sortedStudentDTOList, new Comparator<StudentDTO>() {
            @Override
            public int compare(StudentDTO o1, StudentDTO o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println(sortedStudentDTOList);
        return sortedStudentDTOList;
    }


    public List<StudentDTO> searchByClass(String searchTerm) {
        List<StudentDTO> studentList = new ArrayList<>();
        Connection connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{call FindStudentsByKeyword(?)}");
            callableStatement.setString(1, searchTerm);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                StudentDTO student = new StudentDTO();
                student.setId(resultSet.getInt("Student ID"));
                student.setName(resultSet.getString("Name"));
                student.setBirthday(resultSet.getDate("Birthday"));
                student.setClassName(resultSet.getString("Class"));
                studentList.add(student);
            }
        } catch (SQLException e) {
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return studentList;
    }
}

package com.ra.model.service;

import com.ra.dto.StudentDTO;
import com.ra.model.dao.StudentDAO;

import java.util.List;

public class StudentService implements IGenericService<StudentDTO, Integer> {
    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    public List<StudentDTO> getAll() {
        return studentDAO.getAll();
    }

    @Override
    public boolean save(StudentDTO studentDTO) {
        return studentDAO.save(studentDTO);
    }

    @Override
    public StudentDTO getById(Integer studentId) {
        return studentDAO.getById(studentId);
    }

    @Override
    public boolean update(StudentDTO studentDTO, Integer studentId) {
        return studentDAO.update(studentDTO, studentId);
    }

    @Override
    public boolean delete(Integer studentId) {
        return studentDAO.delete(studentId);
    }

    public List<StudentDTO> getSortedStudentDTOList() {
        return studentDAO.getSortedStudentDTOList();
    }

    public List<StudentDTO> searchByClass(String searchTerm) {
        return studentDAO.searchByClass(searchTerm);
    }
}

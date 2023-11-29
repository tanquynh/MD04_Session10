package com.ra.model.service;

import com.ra.dto.ClassroomDTO;

import com.ra.model.dao.ClassroomDAO;

import java.util.List;

public class ClassroomService implements IGenericService<ClassroomDTO, Integer> {
    private final ClassroomDAO classroomDAO = new ClassroomDAO();

    @Override
    public List<ClassroomDTO> getAll() {
        return classroomDAO.getAll();
    }

    @Override
    public boolean save(ClassroomDTO classroomDTO) {
        return classroomDAO.save(classroomDTO);
    }

    @Override
    public ClassroomDTO getById(Integer classId) {
        return classroomDAO.getById(classId);
    }

    @Override
    public boolean update(ClassroomDTO classroomCTO, Integer classId) {
        return classroomDAO.update(classroomCTO, classId);
    }

    @Override
    public boolean delete(Integer classId) {
        return classroomDAO.delete(classId);
    }
}

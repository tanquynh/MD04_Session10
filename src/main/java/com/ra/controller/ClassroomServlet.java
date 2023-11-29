package com.ra.controller;

import com.ra.dto.ClassroomDTO;
import com.ra.model.service.ClassroomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClassroomServlet", value = "/class")
public class ClassroomServlet extends HttpServlet {
    private ClassroomService classroomService;

    @Override
    public void init() {
        classroomService = new ClassroomService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        List<ClassroomDTO> classroomList = classroomService.getAll();
        request.setAttribute("classroomList", classroomList);
        request.getRequestDispatcher("views/class.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

    }

    @Override
    public void destroy() {

    }

}
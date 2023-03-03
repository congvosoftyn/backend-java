package com.curd.SB.CRUD.controllers;

import com.curd.SB.CRUD.models.Student;
import com.curd.SB.CRUD.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping("/list")
    public List<Student> listStudent(){
        return  studentService.getStudents();
    }
}

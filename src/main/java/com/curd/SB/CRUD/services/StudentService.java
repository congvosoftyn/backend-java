package com.curd.SB.CRUD.services;

import com.curd.SB.CRUD.models.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
    public List<Student> getStudents(){
        return  List.of(
                new Student(1,"cong",12,  LocalDate.of(2000, Month.APRIL,5),"congvo@gmail.com")
        );
    }
}

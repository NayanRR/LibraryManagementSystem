package com.example.Library.Management.SystemAccio.Services;

import com.example.Library.Management.SystemAccio.Controller.StudentController;
import com.example.Library.Management.SystemAccio.Repsoitory.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Library.Management.SystemAccio.Models.*;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String AddStudent(Student student) {
        studentRepository.save(student);
        return "Student is saved in StudentDb";
    }


}

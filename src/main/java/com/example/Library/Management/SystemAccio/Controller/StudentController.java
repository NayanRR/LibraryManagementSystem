package com.example.Library.Management.SystemAccio.Controller;

import com.example.Library.Management.SystemAccio.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Library.Management.SystemAccio.Models.*;

@RestController
@RequestMapping(value="student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/addStudent")
    public ResponseEntity AddStudent(@RequestBody Student student){

        String res=studentService.AddStudent(student);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

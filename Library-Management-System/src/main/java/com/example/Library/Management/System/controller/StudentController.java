package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.dto.requestDTO.StudentRequest;
import com.example.Library.Management.System.dto.responsetDTO.StudentResponse;
import com.example.Library.Management.System.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest){
        StudentResponse response = studentService.addStudent(studentRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
        StudentResponse student = studentService.getStudent(regNo);
        if(student!=null){
            return new ResponseEntity(student,HttpStatus.FOUND);
        }
        return new ResponseEntity("Invalid id!!",HttpStatus.BAD_REQUEST);
    }

    // delete a student --> regNo

    // update the age of a student  ---> regNo, age

    // get all the students in the db  --> findAll()

    // get list of all male students

    @GetMapping("/get-males")
    public List<String> getAllMales(){
        List<String> males = studentService.getAllMales();
        return males;
    }
}

package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.dto.requestDTO.StudentRequest;
import com.example.Library.Management.System.dto.responsetDTO.StudentResponse;
import com.example.Library.Management.System.exception.StudentNotFoundException;
import com.example.Library.Management.System.model.Student;
import com.example.Library.Management.System.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @DeleteMapping("/delete/{regNo}")
    public ResponseEntity deleteByRegNo(@PathVariable int regNo){
        try {
            String response = studentService.deleteByRegNo(regNo);
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        catch (StudentNotFoundException e){
            String response= e.getMessage();
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update/age/{regNo}")
    public ResponseEntity updateAge(@PathVariable int regNo, @RequestParam int newAge){
        try {
            String response= studentService.updateAge(regNo, newAge);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (StudentNotFoundException e){
            String response= e.getMessage();
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    // get all the students in the db  --> findAll()
    @GetMapping("/get/all")
    public ResponseEntity getAllStudent(){
        List<StudentResponse> students= studentService.getAllStudent();
        return new ResponseEntity(students, HttpStatus.FOUND);
    }
    // get list of all male students

    @GetMapping("/get-males")
    public List<String> getAllMales(){
        List<String> males = studentService.getAllMales();
        return males;
    }
}

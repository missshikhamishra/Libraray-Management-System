package com.example.Library.Management.System.service.impl;

import com.example.Library.Management.System.Enum.Gender;
import com.example.Library.Management.System.dto.requestDTO.StudentRequest;
import com.example.Library.Management.System.dto.responsetDTO.StudentResponse;
import com.example.Library.Management.System.model.LibraryCard;
import com.example.Library.Management.System.model.Student;
import com.example.Library.Management.System.repository.StudentRepository;
import com.example.Library.Management.System.transformer.LibraryCardTransformer;
import com.example.Library.Management.System.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentResponse addStudent(StudentRequest studentRequest) {

        // create object using builder
         Student student = StudentTransformer.StudentRequestToStudent(studentRequest);
         LibraryCard card = LibraryCardTransformer.prepareLibraryCard();

         card.setStudent(student);
         student.setLibraryCard(card);  // set librarycard for student
         Student savedStudent = studentRepository.save(student); // save both student and library card

        // saved model to response dto
         return StudentTransformer.StudentToStudentResponse(savedStudent);
    }

    public StudentResponse getStudent(int regNo) {

        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()){
            return StudentTransformer.StudentToStudentResponse(studentOptional.get());
        }
        return null;
    }

    public List<String> getAllMales() {

        List<String> names = new ArrayList<>();
        List<Student> students = studentRepository.findByGender(Gender.MALE);
        for(Student s: students){
            names.add(s.getName());
        }

        return names;
    }

}

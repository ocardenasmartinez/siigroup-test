package com.SQLite.SB.example.service;

import com.SQLite.SB.example.entity.Student;
import com.SQLite.SB.example.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public String createStudent(Student student){
        try {
            if (!studentRepository.existsByEmail(student.getEmail())){
                student.setId(null == studentRepository.findMaxId()? 0 : studentRepository.findMaxId() + 1);
                studentRepository.save(student);
                return "Student record created successfully.";
            }else {
                return "Student already exists in the database.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    public Student readStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    public List<Student> readStudentsWithA() {
        return studentRepository.findAll().stream()
                .filter(student -> student.getName().charAt(0) == 'a')
                .collect(Collectors.toList());
    }


    public List<Student> readStudents(){
        return studentRepository.findAll();
    }
    
  

    @Transactional
    public String updateStudent(Student student){
        if (studentRepository.existsByEmail(student.getEmail())){
            try {
                List<Student> students = studentRepository.findByEmail(student.getEmail());
                students.forEach(s -> {
                    Student studentToBeUpdate = studentRepository.findById(s.getId()).orElseThrow();
                    studentToBeUpdate.setName(student.getName());
                    studentToBeUpdate.setEmail(student.getEmail());
                    studentRepository.save(studentToBeUpdate);
                });
                return "Student record updated.";
            }catch (Exception e){
                throw e;
            }
        }else {
            return "Student does not exists in the database.";
        }
    }

}

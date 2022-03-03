package com.SQLite.SB.example.service;

import com.SQLite.SB.example.dtos.StudentOut;
import com.SQLite.SB.example.entity.Student;
import com.SQLite.SB.example.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public List<Integer> deleteById() {
        var count = new ArrayList<Integer>();
        studentRepository.findAll().stream()
                .map(Student::getId)
                .filter(id -> id > 3)
                .collect(Collectors.toList())
                .forEach(id -> {
                    count.add(id);
                    studentRepository.deleteById(id);
                });
        return count;
    }

    public List<StudentOut> getNewStudents() {
        var newStudents = new ArrayList<StudentOut>();
        studentRepository.findAll().forEach(student -> {
            var newStudent = new StudentOut();
            newStudent.setEmail(student.getEmail());
            newStudent.setName(student.getName());
            newStudents.add(newStudent);
        });
        return newStudents;
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

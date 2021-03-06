package com.SQLite.SB.example.controller;

import com.SQLite.SB.example.dtos.StudentOut;
import com.SQLite.SB.example.entity.Student;
import com.SQLite.SB.example.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/example")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String info(){
        return "The application is up...";
    }

    @RequestMapping(value = "createstudent", method = RequestMethod.POST)
    public String createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @RequestMapping(value = "readstudents", method = RequestMethod.GET)
    public List<Student> readStudents(){
        return studentService.readStudents();
    }

    @RequestMapping(value = "updatestudent", method = RequestMethod.PUT)
    public String updateStudet(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @RequestMapping(value = "readstudentsbyname", method = RequestMethod.GET)
    public Student readStudentsByName(@RequestParam String name){
        return studentService.readStudentsByName(name);
    }

    @RequestMapping(value = "readstudentswitha", method = RequestMethod.GET)
    public List<Student> readStudentsWithA(){
        return studentService.readStudentsWithA();
    }

    @RequestMapping(value = "deletestudents", method = RequestMethod.DELETE)
    public List<Integer> deleteStudents(){
        return studentService.deleteById();
    }

    @RequestMapping(value = "readnewstudents", method = RequestMethod.GET)
    public List<StudentOut> readNewStudents(){
        return studentService.getNewStudents();
    }
    
 
}

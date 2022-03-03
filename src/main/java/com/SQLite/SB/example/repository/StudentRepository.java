package com.SQLite.SB.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SQLite.SB.example.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    List<Student> findByEmail(String email);

    Student findByName(String name);
    
    @Query("select max(s.id) from Student s")
    Integer findMaxId();
}

package com.tommy.example.school;

import com.tommy.example.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByFirstNameContaining(String firstName);
    List<Student> findAllByAge(Integer age);
    List<Student> findAllByOrderByAgeDesc();
}

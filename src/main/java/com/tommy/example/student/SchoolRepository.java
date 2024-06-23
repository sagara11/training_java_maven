package com.tommy.example.student;

import com.tommy.example.school.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {

}

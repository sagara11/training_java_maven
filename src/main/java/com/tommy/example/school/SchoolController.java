package com.tommy.example.school;

import com.tommy.example.student.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {
    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/school")
    @ResponseStatus(HttpStatus.OK)
    public School create(@RequestBody School school){
        return schoolRepository.save(school);
    }

    @GetMapping("/schools")
    public List<School> schools(){
        return schoolRepository.findAll();
    }
}


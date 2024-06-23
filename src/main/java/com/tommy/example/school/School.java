package com.tommy.example.school;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tommy.example.student.Student;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class School {
    @Id
    @GeneratedValue
    private Integer id;

    @JsonProperty("name")
    private String schoolName;

    @OneToMany(
            mappedBy = "school"
    )
    @JsonManagedReference
    private List<Student> students;

    public School(String schoolName) {
        this.schoolName = schoolName;
    }

    public School() {
    }

    public School(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}

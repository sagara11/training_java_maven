package com.tommy.example.studentprofile;

import com.tommy.example.student.Student;
import jakarta.persistence.*;

@Entity
public class StudentProfile {
    @Id
    @GeneratedValue
    private Integer id;

    private String bio;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public StudentProfile() {
    }

    public StudentProfile(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

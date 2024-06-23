package com.tommy.example.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tommy.example.studentprofile.StudentProfile;
import com.tommy.example.school.School;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Column(unique = true)
    private String email;
    private int age;
    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private StudentProfile studentProfile;

    @ManyToOne()
    @JoinColumn(
            name = "school_id"
    )
    @JsonBackReference
    private School school;

    public Student(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student(String firstName, String lastName, String email, int age, School school) {
        this.school = school;
        this.age = age;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

package com.tommy.example.student;

import com.tommy.example.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student toStudent(
            StudentRecord studentRecord
    ) {
        var school = new School(studentRecord.school_id());
        return new Student(
                studentRecord.firstName(),
                studentRecord.lastName(),
                studentRecord.email(),
                20,
                school);
    }

    public StudentResponse toStudentResponse(
            Student student
    ) {
        if (student == null){
            throw  new NullPointerException("The student is null");
        }

        return new StudentResponse(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail());
    }
}

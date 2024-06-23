package com.tommy.example.student;

import com.tommy.example.school.School;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentMapperTest {
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    void shouldMapStudentRecordToStudent(
    ){
        var studentRecord = new StudentRecord(
                "Tommy",
                "Shelby",
                "shelby@gmail.com",
                1
        );

        Student student = studentMapper.toStudent(studentRecord);

        Assertions.assertEquals(studentRecord.firstName(), student.getFirstName());
        Assertions.assertEquals(studentRecord.lastName(), student.getLastName());
        Assertions.assertEquals(studentRecord.email(), student.getEmail());
        Assertions.assertNotNull(student.getSchool());
        Assertions.assertEquals(studentRecord.school_id(), student.getSchool().getId());
    }

    @Test
    void should_throw_error_handler_when_dto_is_null(){
        var exp = Assertions.assertThrows(NullPointerException.class, () -> studentMapper.toStudentResponse(null));
        Assertions.assertEquals(exp.getMessage(), "The student is null");
    }

    @Test
    void shouldMapStudentToStudentResponse(){
        var school = new School(1);
        var student = new Student(
                "Tommy",
                "Shelby",
                "thiensangkdtt@gmail.com",
                20,
                school
        );

        StudentResponse studentResponse = studentMapper.toStudentResponse(student);

        Assertions.assertEquals(student.getFirstName(), studentResponse.firstName());
        Assertions.assertEquals(student.getLastName(), studentResponse.lastName());
        Assertions.assertEquals(student.getEmail(), studentResponse.email());
    }
}
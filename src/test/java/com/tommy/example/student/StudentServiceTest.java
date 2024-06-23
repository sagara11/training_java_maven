package com.tommy.example.student;

import com.tommy.example.school.School;
import com.tommy.example.school.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createStudent() {
        // given
        var studentRecord = new StudentRecord(
                "Tommy",
                "Shelby",
                "thiensangkdtt123@gmail.com",
                1
        );

        var school = new School(1);

        var student = new Student(
                "Tommy",
                "Shelby",
                "thiensangkdtt123@gmail.com",
                20,
                school
        );

        var savedStudent = new Student(
                "Tommy",
                "Shelby",
                "thiensangkdtt123@gmail.com",
                20,
                school
        );
        savedStudent.setId(1);

        //mock
        Mockito.when(studentMapper.toStudent(studentRecord)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn((savedStudent));
        Mockito.when(studentMapper.toStudentResponse(student)).thenReturn(
                new StudentResponse(
                        "Tommy",
                        "Shelby",
                        "thiensangkdtt123@gmail.com"
                )
        );

        // when
        StudentResponse studentResponse = studentService.createStudent(studentRecord);

        //assert
        Assertions.assertEquals("Tommy", studentResponse.firstName());
        Assertions.assertEquals("Shelby", studentResponse.lastName());
        Assertions.assertEquals("thiensangkdtt123@gmail.com", studentResponse.email());

        // verify
        Mockito.verify(studentMapper, Mockito.times(1)).toStudent(studentRecord);
        Mockito.verify(studentRepository, Mockito.times((1))).save(student);
        Mockito.verify(studentMapper, Mockito.times(1)).toStudentResponse(student);
    }

    @Test
    void getAllStudents(){
        // Given
        var school = new School(1);
        var student = new Student(
                "Tommy",
                "Shelby",
                "thiensangkdtt123@gmail.com",
                20,
                school
        );

        List<Student> studentList = new ArrayList<Student>();
        studentList.add(student);

        // Mock
        Mockito.when(studentRepository.findAll()).thenReturn(studentList);
        Mockito.when(studentMapper.toStudentResponse(any(Student.class)))
                .thenReturn(new StudentResponse(
                        "Tommy",
                        "Shelby",
                        "thiensangkdtt123@gmail.com"
                ));

        // When
        List<StudentResponse> results = studentService.getAllStudents();

        // Then
        Assertions.assertEquals(results.size(), studentList.size());
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getStudentById(){
        // Given
        Integer id = 1;
        var school = new School(1);
        var student = new Student(
                "Tommy",
                "Shelby",
                "thiensangkdtt123@gmail.com",
                20,
                school
        );
        student.setId(id);

        // Mock
        Mockito.when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        // When
        Optional<Student> result =  studentService.getStudentById(id);

        // Then
        Assertions.assertEquals(student.getFirstName(), result.orElseThrow().getFirstName());
        Assertions.assertEquals(student.getLastName(), result.orElseThrow().getLastName());
        Assertions.assertEquals(student.getEmail(), result.orElseThrow().getEmail());

        Mockito.verify(studentRepository, Mockito.times(1)).findById(id);
    }
}
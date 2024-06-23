package com.tommy.example.student;

import com.tommy.example.school.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final  StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponse createStudent(
            StudentRecord studentRecord
    ){
        var student = studentMapper.toStudent(studentRecord);
        studentRepository.save(student);
        return studentMapper.toStudentResponse(student);
    }

    public List<StudentResponse> getAllStudents(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponse)
                .toList();
    }

    public Optional<Student> getStudentById(Integer id){
        return studentRepository.findById(id);
    }

    public List<Student> getStudentByName(String name){
        return studentRepository.findAllByFirstNameContaining(name);
    }
}

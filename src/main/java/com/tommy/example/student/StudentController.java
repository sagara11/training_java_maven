package com.tommy.example.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponse students(
            @Valid @RequestBody StudentRecord studentRecord) {
        return studentService.createStudent(studentRecord);
    }

    @GetMapping("/students")
    public List<StudentResponse> students() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{student_id}")
    public Optional<Student> student(@PathVariable("student_id") Integer id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/students/search/name/{student_name}")
    public List<Student> findByName(@PathVariable("student_name") String name) {
        return studentService.getStudentByName(name);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ){
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors().forEach(
                error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
            }
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}

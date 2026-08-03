package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Student>>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Student> students = studentRepository.findAll(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Students retrieved successfully", students));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return ResponseEntity.ok(new ApiResponse<>(true, "Student found", student));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@Valid @RequestBody Student student) {
        Student saved = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Student created successfully", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setCourse(student.getCourse());
        Student updated = studentRepository.save(existing);
        return ResponseEntity.ok(new ApiResponse<>(true, "Student updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Student deleted successfully", null));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Student>>> searchByName(@RequestParam String name) {
        List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(new ApiResponse<>(true, "Found " + students.size() + " students", students));
    }
}
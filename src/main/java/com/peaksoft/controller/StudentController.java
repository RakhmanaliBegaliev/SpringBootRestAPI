package com.peaksoft.controller;

import com.peaksoft.dto.StudentRequest;
import com.peaksoft.dto.StudentResponse;
import com.peaksoft.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAll();
    }

    @PostMapping
    public StudentResponse save(@RequestBody StudentRequest studentRequest) {
        return studentService.create(studentRequest);
    }

    @GetMapping("{id}")
    public StudentResponse getById(@PathVariable("id") Long id) {
        return studentService.getById(id);
    }

    @PutMapping("{id}")
    public StudentResponse update(@PathVariable("id") Long id, @RequestBody StudentRequest studentRequest) {
        return studentService.update(id, studentRequest);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        studentService.deleteById(id);
        return "User with Id:" + id + "was deleted";
    }

}

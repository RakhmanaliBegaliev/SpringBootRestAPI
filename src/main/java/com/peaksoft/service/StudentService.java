package com.peaksoft.service;

import com.peaksoft.dto.StudentRequest;
import com.peaksoft.dto.StudentResponse;
import com.peaksoft.entity.Student;
import com.peaksoft.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student mapToStudent(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setAge(request.getAge());
        return student;
    }

    public StudentResponse mapToResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setName(student.getName());
        response.setSurname(student.getSurname());
        response.setAge(student.getAge());
        response.setIsActive(student.getIsActive());
        response.setCreated(student.getCreated());
        return response;
    }

    public StudentResponse create(StudentRequest studentRequest) {
        Student student = mapToStudent(studentRequest);
        student.setCreated(LocalDate.now());
        studentRepository.save(student);
        return mapToResponse(student);
    }

    public List<StudentResponse> getAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student : students) {
            studentResponses.add(mapToResponse(student));
        }
        return studentResponses;
    }

    public StudentResponse update(Long id, StudentRequest studentRequest) {
        Student student = studentRepository.getById(id);
        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setAge(studentRequest.getAge());
        studentRepository.save(student);
        return mapToResponse(student);
    }

    public StudentResponse getById(Long id) {
        Student student = studentRepository.getById(id);
        return mapToResponse(student);
    }

    public void deleteById(Long id) {
        Student student = studentRepository.getById(id);
        studentRepository.delete(student);
    }
}

package com.sunglowsys.service;

import com.sunglowsys.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentService {
    Student saveStudent(Student student);

    Student updateStudent(Student student);

    Page<Student> getAllStudents(Pageable pageable);

    Optional<Student> findById(Long id);

    // List<Student> search(String searchText);

    void deleteStudent(Long id);
}

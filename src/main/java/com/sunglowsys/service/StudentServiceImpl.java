package com.sunglowsys.service;

import com.sunglowsys.domain.Student;
import com.sunglowsys.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        log.debug("Request to save Students: {}",student);
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        log.debug("Request to update Students: {}",student);
        return studentRepository.save(student);

    }

    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        log.debug("Request to getAll Students: {}",pageable);
        return studentRepository.findAll(pageable);
    }

    @Override
    public Optional<Student> findById(Long id) {
        log.debug("Request to find Student ById: {} ",id);
        return studentRepository.findById(id);
    }

    @Override
    public void deleteStudent(Long id) {
        log.debug("Request to  delete Students: {}",id);
        studentRepository.deleteById(id);
    }
}

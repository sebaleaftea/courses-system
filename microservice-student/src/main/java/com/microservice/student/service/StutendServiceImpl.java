package com.microservice.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.student.model.Student;
import com.microservice.student.repository.StudentRepository;


@Service
public class StutendServiceImpl implements IStudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow();//Si no lo encuentra
        //Entonces lanza un error
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> findByIdCourse(Long idCourse) {
        //Este es el que creamos con la notación query o con el nombre del metodo
        return studentRepository.findAllStudent(idCourse);
    }

}

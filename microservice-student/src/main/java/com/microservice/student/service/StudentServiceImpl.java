package com.microservice.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.student.model.Student;
import com.microservice.student.repository.StudentRepository;


@Service
public class StudentServiceImpl implements IStudentService{

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
    public Student updateStudent(Student student) {
        Optional<Student> existingStudentOpt = studentRepository.findById(student.getId());

        if (existingStudentOpt.isEmpty()) {
        return null;
        }

        Student existingStudent = existingStudentOpt.get();

        existingStudent.setName(student.getName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCourseId(student.getCourseId());
        existingStudent.setCertificateId(student.getCertificateId());
        existingStudent.setEnrollmentId(student.getEnrollmentId());
        existingStudent.setSupportId(student.getSupportId());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteById(Long id){
        studentRepository.deleteById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> findByIdCourse(Long idCourse) {
        //Este es el que creamos con la notación query o con el nombre del metodo
        return studentRepository.findAllByCourseId(idCourse);
    }

    @Override
    public List<Student> findByIdEnrollment(Long idEnrollment) {
        return studentRepository.findAllByEnrollmentId(idEnrollment);
    }

    @Override
    public List<Student> findByIdCertificate(Long idCertificate) {
        //Este es el que creamos con la notación query o con el nombre del metodo
        return studentRepository.findAllByCertificateId(idCertificate);
    }

    @Override
    public List<Student> findByIdSupport(Long idSupport) {
        //Este es el que creamos con la notación query o con el nombre del metodo
        return studentRepository.findAllBySupportId(idSupport);
    }


}

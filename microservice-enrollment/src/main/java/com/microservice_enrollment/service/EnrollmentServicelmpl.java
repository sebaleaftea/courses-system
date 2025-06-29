package com.microservice_enrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice_enrollment.client.StudentClient;
import com.microservice_enrollment.dto.StudentDTO;
import com.microservice_enrollment.http.response.StudentByEnrollmentResponse;
import com.microservice_enrollment.model.Enrollment;
import com.microservice_enrollment.repository.IEnrollmentRepository;

@Service
public class EnrollmentServicelmpl implements IEnrollmentService{

    @Autowired
    private  IEnrollmentRepository iEnrollmentRepository;

    @Autowired
    private StudentClient studentClient;

    @Override
    public List<Enrollment> findAll(){
        return (List<Enrollment>) iEnrollmentRepository.findAll();
    }

    @Override
    public Enrollment findById(Long id){
        return iEnrollmentRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Enrollment enrollment){
        iEnrollmentRepository.save(enrollment);
    }

    @Override
    public StudentByEnrollmentResponse findStudentsByIdEnrollment(Long idEnrollment){

        //Consultar el curso
        //Porque devuelve un optional
        Enrollment enrollment = iEnrollmentRepository.findById(idEnrollment).orElse(new Enrollment());

        //Obtener los estudiantes que estan en el curso obtenido
        List<StudentDTO> studentDTOList = studentClient.findAllStudentByEnrollment(idEnrollment);


        return StudentByEnrollmentResponse.builder()
                .pay(enrollment.getPay())
                .expiration(enrollment.getExpiration())
                .status(enrollment.getStatus())
                .studentDTOList(studentDTOList)
                .build();
    }
}

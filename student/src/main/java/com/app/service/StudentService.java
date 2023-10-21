package com.app.service;

import com.app.dto.AddressResponse;
import com.app.dto.CreateStudentRequest;
import com.app.dto.StudentResponse;

import com.app.entity.Student;
import com.app.feignclients.AddressFeignClient;
import com.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /*@Autowired
    private WebClient webClient;*/

    @Autowired
    private AddressFeignClient addressFeignClient;


    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());

        student.setAddressId(createStudentRequest.getAddressId());
        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);

//        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
        studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));

        return studentResponse;
    }

    private AddressResponse getAddressById(long addressId) {
        return null;
    }

    public StudentResponse getById(long id) {
        Student student = studentRepository.findById(id).get();
        StudentResponse studentResponse = new StudentResponse(student);

//		studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
        studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));

        return studentResponse;
    }

    /*public StudentResponse getById(long id) {
        Student student = studentRepository.findById(id).get();
        StudentResponse studentResponse = new StudentResponse(student);

        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

        return studentResponse;
    }*/

    /*public AddressResponse getAddressById(long addressId) {
        Mono<AddressResponse> addressResponse = webClient.get()
                .uri("/getById/" + addressId)
                .retrieve()
                .bodyToMono(AddressResponse.class);

        return addressResponse.block();
    }*/
}

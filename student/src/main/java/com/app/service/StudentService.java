package com.app.service;

import com.app.dto.AddressResponse;
import com.app.dto.CreateStudentRequest;
import com.app.dto.StudentResponse;

import com.app.entity.Student;
import com.app.feignclients.AddressFeignClient;
import com.app.repository.StudentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /*@Autowired
    private WebClient webClient;*/

    @Autowired
    private AddressFeignClient addressFeignClient;

    @Autowired
    private CommonService commonService;


    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());

        student.setAddressId(createStudentRequest.getAddressId());
        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);

//        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
        studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));

        return studentResponse;
    }


    /*@CircuitBreaker(
            name="addressService",
            fallbackMethod = "fallbackGetAddressById"
    )
    private AddressResponse getAddressById(long addressId) {
        AddressResponse response = addressFeignClient.getById(addressId);
            return response;
    }

    private AddressResponse fallbackGetAddressById(long addressId, Throwable throwable) {
        log.warn("fallbackGetAddressById");
        return new AddressResponse();
    }*/

    /*public StudentResponse getById(long id) {
        Student student = studentRepository.findById(id).get();
        StudentResponse studentResponse = new StudentResponse(student);

//		studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));

        return studentResponse;
    }
*/
    public StudentResponse getById(long id) {
        log.info("Inside student service getById");
        Student student = studentRepository.findById(id).get();
        StudentResponse studentResponse = new StudentResponse(student);

        studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));

        return studentResponse;
    }

    /*public AddressResponse getAddressById(long addressId) {
        Mono<AddressResponse> addressResponse = webClient.get()
                .uri("/getById/" + addressId)
                .retrieve()
                .bodyToMono(AddressResponse.class);

        return addressResponse.block();
    }*/
}

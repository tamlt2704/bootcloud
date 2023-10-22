package com.app.feignclients;

import com.app.dto.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


// Below can be used when you're not using Eureka-server
//@FeignClient(url = "${address.service.url}",
//        value = "address-feign-client",
//        path = "/api/address")

//@FeignClient(value = "address-service",
//        path = "/api/address")

@FeignClient(value = "api-gateway")
public interface AddressFeignClient {
    @GetMapping("/address-service/api/address/getById/{id}")
    public AddressResponse getById(@PathVariable long id);
}
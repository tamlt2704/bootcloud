package com.app.feignclients;

import com.app.dto.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


// Below can be used when you're not using Eureka-server
@FeignClient(url = "${address.service.url}",
        value = "address-feign-client",
        path = "/api/address")
public interface AddressFeignClient {

    @GetMapping("/getById/{id}")
    public AddressResponse getById(@PathVariable long id);
}
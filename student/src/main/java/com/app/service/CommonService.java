package com.app.service;

import com.app.dto.AddressResponse;
import com.app.feignclients.AddressFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CommonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    private AddressFeignClient addressFeignClient;

    long count = 1;

    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(long addressId) {
        LOGGER.info("Count =" + count);
        count++;

        AddressResponse addressResponse = addressFeignClient.getById(addressId);
        return addressResponse;
    }

    public AddressResponse fallbackGetAddressById(long addressId, Throwable throwable) {
        LOGGER.warn(">>> Fallback called :" + throwable.getMessage());
        return new AddressResponse();
    }
}
package com.app.service;

import com.app.dto.AddressResponse;
import com.app.dto.CreateAddressRequest;

public interface AddressService {
    public AddressResponse createAddress(CreateAddressRequest createAddressRequest);
    public AddressResponse getById (long id);
}
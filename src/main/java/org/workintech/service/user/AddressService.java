package org.workintech.service.user;

import org.workintech.dto.user.AddressResponse;
import org.workintech.entity.user.Address;

import java.util.List;

public interface AddressService {
    Address findById(Long id);
    AddressResponse save(Address address);
    AddressResponse delete(Long id);
    AddressResponse update(Long id, Address address);
    AddressResponse getById(Long id);
    List<AddressResponse> getAll(String token);

}

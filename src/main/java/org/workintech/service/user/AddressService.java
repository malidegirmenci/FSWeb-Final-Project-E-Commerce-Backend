package org.workintech.service.user;

import org.workintech.dto.user.AddressResponse;
import org.workintech.entity.user.Address;

import java.util.List;

public interface AddressService {
    Address findById(Long id);
    AddressResponse save(Address address, String token);
    String delete(String token, Long addressId);
    AddressResponse update(Long id, Address address);
    AddressResponse getById(Long id);
    List<AddressResponse> getAll();
    List<AddressResponse> getByUserToken(String token);

}

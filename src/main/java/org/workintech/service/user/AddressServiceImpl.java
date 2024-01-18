package org.workintech.service.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.workintech.converter.DtoConverter;
import org.workintech.dto.user.AddressResponse;
import org.workintech.entity.user.Address;
import org.workintech.entity.user.User;
import org.workintech.exceptions.EcommerceException;
import org.workintech.repository.user.AddressRepository;
import org.workintech.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private UserRepository userRepository;

    @Override
    public Address findById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            return optionalAddress.get();
        }
        throw new EcommerceException("The address with given id does not exist. ID: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public AddressResponse save(Address address, String token) {
        Optional<User> optionalUser = userRepository.findUserByToken(token);
        if(optionalUser.isPresent()){
            optionalUser.get().getAddresses().add(address);
            return DtoConverter.convertToAddressResponse(addressRepository.save(address));
        }
        throw new EcommerceException("The given token is wrong. So the address could not saved",HttpStatus.UNAUTHORIZED);
    }

    @Override
    public AddressResponse delete(Long id) {
        Address willDeleteAddress = findById(id);
        addressRepository.delete(willDeleteAddress);
        return DtoConverter.convertToAddressResponse(willDeleteAddress);
    }

    @Override
    public AddressResponse update(Long id, Address address) {
        Address willUpdateAddress = findById(id);
        willUpdateAddress.setAddressDetail(address.getAddressDetail());
        willUpdateAddress.setName(address.getName());
        willUpdateAddress.setSurname(address.getSurname());
        willUpdateAddress.setPhone(address.getPhone());
        willUpdateAddress.setCity(address.getName());
        willUpdateAddress.setDistrict(address.getDistrict());
        willUpdateAddress.setNeighborhood(address.getNeighborhood());
        willUpdateAddress.setAddressDetail(address.getAddressTitle());
        return DtoConverter.convertToAddressResponse(addressRepository.save(willUpdateAddress));
    }

    @Override
    public AddressResponse getById(Long id) {
        return DtoConverter.convertToAddressResponse(findById(id));
    }

    @Override
    public List<AddressResponse> getAll() {
        return DtoConverter.convertToAddressResponseList(addressRepository.findAll());
    }

    @Override
    public List<AddressResponse> getByUserToken(String token) {
        Optional<User> user = userRepository.findUserByToken(token);
        return DtoConverter.convertToAddressResponseList(user.orElseThrow().getAddresses().stream().toList());
    }
}

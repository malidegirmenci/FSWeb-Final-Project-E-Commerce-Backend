package org.workintech.dto.user;

import org.workintech.entity.user.Address;

public record AddressRequest(Address address, String token) {
}

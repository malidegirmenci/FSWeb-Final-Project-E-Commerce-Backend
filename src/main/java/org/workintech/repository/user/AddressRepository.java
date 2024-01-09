package org.workintech.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workintech.entity.user.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {
}

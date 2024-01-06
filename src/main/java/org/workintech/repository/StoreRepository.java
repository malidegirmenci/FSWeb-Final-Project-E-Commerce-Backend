package org.workintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workintech.entity.Store;

public interface StoreRepository extends JpaRepository<Store,Long> {
}

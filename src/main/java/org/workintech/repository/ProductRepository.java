package org.workintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workintech.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

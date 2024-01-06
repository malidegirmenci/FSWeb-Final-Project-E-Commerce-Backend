package org.workintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.workintech.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

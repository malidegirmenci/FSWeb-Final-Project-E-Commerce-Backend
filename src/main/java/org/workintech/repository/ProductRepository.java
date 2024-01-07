package org.workintech.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.workintech.entity.Product;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    long count();
    @Query(value = "SELECT p FROM Product p " +
            "WHERE (:category IS NULL OR p.category.id = :category) " +
            "AND (:filter IS NULL OR (LOWER(p.name) LIKE LOWER(CONCAT('%', :filter, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :filter, '%')))) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price:asc' THEN p.price END ASC, " +
            "CASE WHEN :sort = 'price:desc' THEN p.price END DESC, " +
            "CASE WHEN :sort = 'rating:asc' THEN p.rating END ASC, " +
            "CASE WHEN :sort = 'rating:desc' THEN p.rating END DESC ")
    List<Product> findAllBy(
            @Param("category")Integer category,
            @Param("filter")String filter,
            @Param("sort")String sort,
            Pageable pageable);
    @Query(value = "SELECT p FROM Product p " +
            "WHERE (:category IS NULL OR p.category.id = :category) " +
            "AND (:filter IS NULL OR (LOWER(p.name) LIKE LOWER(CONCAT('%', :filter, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :filter, '%')))) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price:asc' THEN p.price END ASC, " +
            "CASE WHEN :sort = 'price:desc' THEN p.price END DESC, " +
            "CASE WHEN :sort = 'rating:asc' THEN p.rating END ASC, " +
            "CASE WHEN :sort = 'rating:desc' THEN p.rating END DESC ")
    List<Product> countFindAllBy(
            @Param("category")Integer category,
            @Param("filter")String filter,
            @Param("sort")String sort
            );

}

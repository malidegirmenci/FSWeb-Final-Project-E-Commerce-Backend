package org.workintech.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.workintech.converter.DtoConverter;
import org.workintech.dto.ProductResponse;
import org.workintech.entity.Product;
import org.workintech.exceptions.EcommerceException;
import org.workintech.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product finById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw new EcommerceException("The product with given id does not exist. ID: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public ProductResponse save(Product product) {
        return DtoConverter.convertToProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse getById(Long id) {
        return DtoConverter.convertToProductResponse(finById(id));
    }

    @Override
    public ProductResponse update(Long id, Product product) {
        Product willUpdateProduct = finById(id);
        willUpdateProduct.setName(product.getName());
        willUpdateProduct.setDescription(product.getDescription());
        willUpdateProduct.setImages(product.getImages());
        willUpdateProduct.setPrice(product.getPrice());
        willUpdateProduct.setRating(product.getRating());
        willUpdateProduct.setStock(product.getStock());
        willUpdateProduct.setSellCount(product.getSellCount());
        return DtoConverter.convertToProductResponse(productRepository.save(willUpdateProduct));
    }

    @Override
    public ProductResponse delete(Long id) {
        Product willDeleteProduct = finById(id);
        productRepository.delete(willDeleteProduct);
        return DtoConverter.convertToProductResponse(willDeleteProduct);
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public List<ProductResponse> getAllWithParams(Integer category, String filter, String sort, Pageable pageable) {
        return DtoConverter.convertToProductResponseList(productRepository.findAllBy(category, filter, sort, pageable));
    }

    @Override
    public List<ProductResponse> countAllWithParams(Integer category, String filter, String sort) {
        return DtoConverter.convertToProductResponseList(productRepository.countFindAllBy(category, filter, sort));
    }

}

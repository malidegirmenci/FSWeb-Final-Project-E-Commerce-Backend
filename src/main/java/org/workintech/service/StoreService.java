package org.workintech.service;

import org.workintech.dto.StoreResponse;
import org.workintech.entity.Store;

import java.util.List;

public interface StoreService {
    Store findById(Long id);
    StoreResponse save(Store store);
    Store saveStore(Store store);
    StoreResponse getById(Long id);
    StoreResponse update(Long id, Store store);
    StoreResponse delete(Long id);
    void saveAll(List<Store> stores);
    List<StoreResponse> getAll();
}

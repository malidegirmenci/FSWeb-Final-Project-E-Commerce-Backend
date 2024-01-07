package org.workintech.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.workintech.converter.DtoConverter;
import org.workintech.dto.StoreResponse;
import org.workintech.entity.Store;
import org.workintech.exceptions.EcommerceException;
import org.workintech.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StoreServiceImpl implements StoreService{
    private final StoreRepository storeRepository;

    @Override
    public Store findById(Long id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        if(storeOptional.isPresent()){
            return storeOptional.get();
        }
        throw new EcommerceException("The store with given id does not exist. ID: "+id, HttpStatus.NOT_FOUND);
    }

    @Override
    public StoreResponse save(Store store) {
        return DtoConverter.convertToStoreResponse(storeRepository.save(store));
    }

    @Override
    public StoreResponse getById(Long id) {
        return DtoConverter.convertToStoreResponse(findById(id));
    }

    @Override
    public StoreResponse update(Long id, Store store) {
        Store willUpdateStore = findById(id);
        willUpdateStore.setName(store.getName());
        willUpdateStore.setPhone(store.getPhone());
        willUpdateStore.setIban(store.getIban());
        willUpdateStore.setTaxNumber(store.getIban());
        willUpdateStore.setProducts(store.getProducts());
        return DtoConverter.convertToStoreResponse(storeRepository.save(willUpdateStore));
    }

    @Override
    public StoreResponse delete(Long id) {
        Store willDeleteStore= findById(id);
        storeRepository.delete(willDeleteStore);
        return DtoConverter.convertToStoreResponse(willDeleteStore);
    }

    @Override
    public void saveAll(List<Store> stores) {
        DtoConverter.convertToStoreResponseList(storeRepository.saveAll(stores));
    }

    @Override
    public List<StoreResponse> getAll() {
        return DtoConverter.convertToStoreResponseList(storeRepository.findAll());
    }

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }
}

package org.workintech.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.workintech.entity.ImagesObj;
import org.workintech.repository.ImagesObjRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ImagesObjServiceImpl implements ImagesObjService {
    private ImagesObjRepository imagesObjRepository;

    public ImagesObj save(ImagesObj imagesObj) {
        return imagesObjRepository.save(imagesObj);
    }

    public List<ImagesObj> saveAll(List<ImagesObj> imagesObjs) {
        return imagesObjRepository.saveAll(imagesObjs);
    }
}

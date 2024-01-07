package org.workintech.service;

import org.workintech.entity.ImagesObj;

import java.util.List;

public interface ImagesObjService {
    ImagesObj save(ImagesObj imagesObj);
    List<ImagesObj> saveAll(List<ImagesObj> imagesObjs);
}

package com.srapi.forcrud.service;

import com.srapi.forcrud.entity.MyEntity;

import java.util.List;


public interface Service {

    List<MyEntity> getAll();
    MyEntity getById(Long id);
    MyEntity createOrUpdate(MyEntity myEntity);
    boolean deleteById(Long id);
}

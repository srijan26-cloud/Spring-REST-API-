package com.srapi.forcrud.service;

import com.srapi.forcrud.dao.Repository;
import com.srapi.forcrud.entity.MyEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    private Repository repo;

    @Override
    public List<MyEntity> getAll() {
        return repo.findAll();
    }

    @Override
    public MyEntity getById(Long id) {
        Optional<MyEntity> getEntity = repo.findById(id);
        return getEntity.orElse(null);
    }

    @Override
    public MyEntity createOrUpdate(MyEntity myEntity) {
        repo.save(myEntity);
        return myEntity;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<MyEntity> getEntity = repo.findById(id);
        if(getEntity.isPresent())
        {
            repo.delete(getEntity.get());
            return true;
        }
        return false;
    }

}

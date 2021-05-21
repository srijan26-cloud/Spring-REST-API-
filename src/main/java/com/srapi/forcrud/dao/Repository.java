package com.srapi.forcrud.dao;

import com.srapi.forcrud.entity.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<MyEntity, Long> {

    @Override
    List<MyEntity> findAll();
}

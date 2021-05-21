package com.srapi.forcrud.controller;

/*
  @Author SRIJAN CHANDRA
 * */

import com.srapi.forcrud.entity.MyEntity;
import com.srapi.forcrud.service.Service;
import com.srapi.forcrud.service.ValidationErrorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Controller {

    @Autowired
    private Service sr;

    @Autowired
    private ValidationErrorService validService;

    @GetMapping
    public ResponseEntity<?> getAllData(){
        return new ResponseEntity<>(sr.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getParticularData(@PathVariable Long id){

        return new ResponseEntity<>(sr.getById(id) , HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> createNewData(@Valid @RequestBody MyEntity myEntity , BindingResult result){
        ResponseEntity<?> errors = validService.validation(result);
        if(errors != null)  return errors;

        //else
        MyEntity savedMyEntity = sr.createOrUpdate(myEntity);
        return new ResponseEntity<MyEntity>(savedMyEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateParticularData(@PathVariable Long id,@Valid @RequestBody MyEntity myEntity, BindingResult result){
        ResponseEntity<?> errors = validService.validation(result);
        if(errors != null)  return errors;

        //else
        myEntity.setId(id);
        MyEntity savedMyEntity = sr.createOrUpdate(myEntity);
        return new ResponseEntity<MyEntity>(savedMyEntity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParticularData(@PathVariable Long id) {
        if(sr.deleteById(id) == true)
            return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
        
        return new ResponseEntity<String>("Id Not Found",HttpStatus.OK);
    }
}

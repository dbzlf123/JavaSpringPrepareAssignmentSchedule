package com.example.JavaSpringPrepareAssignment.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/** 안씀 공부용으로 다뤄봤음**/

public interface ManagementService<T, R, E> {

    public ResponseEntity<R> create(@Valid @RequestBody T requestDto);
    public List<R> getLists();
    public int update(@PathVariable int id, @RequestBody T requestDto);
    public int delete(@PathVariable int id);
    public E findById(int id);

}

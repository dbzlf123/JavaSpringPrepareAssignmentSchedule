package com.example.JavaSpringPrepareAssignment.service;

import com.example.JavaSpringPrepareAssignment.dto.managerdto.ManagerRequestDto;
import com.example.JavaSpringPrepareAssignment.dto.managerdto.ManagerResponseDto;
import com.example.JavaSpringPrepareAssignment.exception.DataNotFoundException;
import com.example.JavaSpringPrepareAssignment.entity.Manager;
import com.example.JavaSpringPrepareAssignment.reopository.managerrepository.ManagerRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Service
public class ManagerService {

    //private final JdbcTemplate jdbcTemplate;
    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public ResponseEntity<ManagerResponseDto> create(@Valid @RequestBody ManagerRequestDto requestDto){
        Manager manager = new Manager(requestDto);
        return ResponseEntity.ok(new ManagerResponseDto( managerRepository.create(manager)));
    }

    public List<ManagerResponseDto> getLists(){
        return managerRepository.findAll().stream().map(ManagerResponseDto::new).toList();
    }

    public Manager update(@PathVariable int id, @RequestBody ManagerRequestDto requestDto){
        Manager manager = managerRepository.findById(id);

        if(manager != null){
            manager.setName(requestDto.getName());
            manager.setEmail(requestDto.getEmail());
            return managerRepository.update(id, manager);
        }else{
            throw new DataNotFoundException("선택한 관리자는 존재하지 않습니다.");
       }
    }

    public String delete(@PathVariable int id){

        Manager manager = managerRepository.findById(id);
        if (manager != null) {
            return managerRepository.delete(id);
        } else {
            throw new DataNotFoundException("선택한 관리자는 존재하지 않습니다.");
        }
    }

    public Manager findById(int id){
        Manager manager = managerRepository.findById(id);
        if (manager != null) {
            return manager;
        }else{
            throw new DataNotFoundException("선택한 관리자는 존재하지 않습니다.");
        }
    }
}

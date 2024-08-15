package com.example.JavaSpringPrepareAssignment.controller;

import com.example.JavaSpringPrepareAssignment.dto.managerdto.ManagerRequestDto;
import com.example.JavaSpringPrepareAssignment.dto.managerdto.ManagerResponseDto;
import com.example.JavaSpringPrepareAssignment.entity.Manager;
import com.example.JavaSpringPrepareAssignment.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ManagerController implements ManagementController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/managers")
    public ResponseEntity<ManagerResponseDto> createManager(@Valid @RequestBody ManagerRequestDto requestDto){
        return ResponseEntity.ok(managerService.create(requestDto));
    }

    @GetMapping("/managers/{id}")
    public Manager getManager(@PathVariable int id){return managerService.findById(id);}

    @GetMapping("/managers")
    public List<ManagerResponseDto> getManagers(){
        return managerService.getLists();
    }

    @PutMapping("/managers/{id}")
    public Manager updateManager(@PathVariable int id, @RequestBody ManagerRequestDto requestDto){
        return managerService.update(id, requestDto);
    }

    @DeleteMapping("/managers/{id}")
    public String deleteManager(@PathVariable int id){
        return managerService.delete(id);
    }
}

package com.example.JavaSpringPrepareAssignment.dto.managerdto;

import com.example.JavaSpringPrepareAssignment.entity.Manager;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ManagerResponseDto{

    private int managerId;
    private String name;
    private String email;
    private LocalDateTime RegistrationDate; // 등록 날짜
    private LocalDateTime ModificationDate; // 수정 날짜

    public ManagerResponseDto(Manager manager) {
        this.managerId = manager.getManagerId();
        this.name = manager.getName();
        this.email = manager.getEmail();
        RegistrationDate = manager.getRegistrationDate();
        ModificationDate = manager.getRegistrationDate();
    }
}

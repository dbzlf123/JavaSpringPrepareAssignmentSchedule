package com.example.JavaSpringPrepareAssignment.entity;

import com.example.JavaSpringPrepareAssignment.dto.managerdto.ManagerRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Getter
@Setter
@NoArgsConstructor
public class Manager{

    private int managerId;
    private String name;
    private String email;

    private LocalDateTime RegistrationDate; // 등록 날짜
    private LocalDateTime ModificationDate; // 수정 날짜

    public Manager(ManagerRequestDto requestDto) {
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
        this.RegistrationDate = requestDto.getRegistrationDate();
        this.ModificationDate = requestDto.getModificationDate();
    }


}

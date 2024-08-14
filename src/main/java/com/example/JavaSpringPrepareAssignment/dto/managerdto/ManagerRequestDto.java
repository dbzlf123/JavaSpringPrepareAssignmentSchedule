package com.example.JavaSpringPrepareAssignment.dto.managerdto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ManagerRequestDto{
    private int managerId;
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    private LocalDateTime RegistrationDate; // 등록 날짜
    private LocalDateTime ModificationDate; // 수정 날짜
}

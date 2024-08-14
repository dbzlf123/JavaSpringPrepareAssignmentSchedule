package com.example.JavaSpringPrepareAssignment.dto.scheduledto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto{

    @NotNull
    @Size(max = 200, message = "할일의 길이가 너무 깁니다.")
    private String toDo;
    @NotBlank
    @Size(max = 100, message = "비밀번호가 너무 깁니다.")
    private String password;
    private LocalDateTime registrationDate; // 등록 날짜
    private LocalDateTime modificationDate; // 수정 날짜
    private int managerId;
}

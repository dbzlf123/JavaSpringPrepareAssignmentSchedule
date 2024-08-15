package com.example.JavaSpringPrepareAssignment.entity;

import com.example.JavaSpringPrepareAssignment.dto.scheduledto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Schedule{

    private int scheduleId;
    private String toDo;
    private String password;
    private LocalDateTime RegistrationDate; // 등록 날짜
    private LocalDateTime ModificationDate; // 수정 날짜
    private int managerId;

    public Schedule(ScheduleRequestDto requestDto) {
        this.toDo = requestDto.getToDo();
        this.password = requestDto.getPassword();
        this.RegistrationDate = requestDto.getRegistrationDate();
        this.ModificationDate = requestDto.getModificationDate();
        this.managerId = requestDto.getManagerId();
    }
}

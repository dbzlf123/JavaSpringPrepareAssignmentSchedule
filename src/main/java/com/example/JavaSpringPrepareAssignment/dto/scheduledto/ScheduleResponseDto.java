package com.example.JavaSpringPrepareAssignment.dto.scheduledto;

import com.example.JavaSpringPrepareAssignment.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleResponseDto{

    private int scheduleId;
    private String toDo;
    private LocalDateTime RegistrationDate; // 등록 날짜
    private LocalDateTime ModificationDate; // 수정 날짜
    private int managerId;

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.toDo = schedule.getToDo();
        this.RegistrationDate = schedule.getRegistrationDate();
        this.ModificationDate = schedule.getModificationDate();
        this.managerId = schedule.getManagerId();
    }

}

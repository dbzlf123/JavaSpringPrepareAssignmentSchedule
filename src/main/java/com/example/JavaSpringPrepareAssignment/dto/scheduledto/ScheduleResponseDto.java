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
    private String name;

    public ScheduleResponseDto(int scheduleId, String toDo, LocalDateTime registrationDate, LocalDateTime modificationDate, int managerId) {
        this.scheduleId = scheduleId;
        this.toDo = toDo;
        this.RegistrationDate = registrationDate;
        this.ModificationDate = modificationDate;
        this.managerId = managerId;
    }

    public ScheduleResponseDto(int scheduleId, String toDo, LocalDateTime registrationDate, LocalDateTime modificationDate, int managerId, String name) {
        this.scheduleId = scheduleId;
        this.toDo = toDo;
        this.RegistrationDate = registrationDate;
        this.ModificationDate = modificationDate;
        this.managerId = managerId;
        this.name = name;
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.toDo = schedule.getToDo();
        this.RegistrationDate = schedule.getRegistrationDate();
        this.ModificationDate = schedule.getModificationDate();
        this.managerId = schedule.getManagerId();
    }

    public ScheduleResponseDto(){

    }
}

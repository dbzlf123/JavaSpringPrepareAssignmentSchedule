package com.example.JavaSpringPrepareAssignment.controller;

import com.example.JavaSpringPrepareAssignment.dto.scheduledto.ScheduleRequestDto;
import com.example.JavaSpringPrepareAssignment.dto.scheduledto.ScheduleResponseDto;
import com.example.JavaSpringPrepareAssignment.entity.Schedule;
import com.example.JavaSpringPrepareAssignment.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class ScheduleController implements ManagementController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody @Valid ScheduleRequestDto requestDto){
        return scheduleService.create(requestDto).getBody();
    }

    //단건 조회
    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable int id){
       return scheduleService.getSchedule(id);
    }

    //다중 조회
    @GetMapping("/schedules/schedules")
    public List<ScheduleResponseDto> getSchedules(@RequestParam(required = false) int id,
                                                  @RequestParam(required = false) String modificationDate){
       return scheduleService.getSchedules(id, modificationDate);
    }

    //페이징 조회
    @GetMapping("/schedules/schedules/list")
    public List<ScheduleResponseDto> getSchedulesByPaging(@RequestParam int pageNumber, @RequestParam int size){
        return scheduleService.getSchedulesByPaging(pageNumber, size);
    }

    @PutMapping("/schedules/edit")
    public ScheduleResponseDto updateSchedule(@RequestParam int id, @RequestParam String password, @RequestBody ScheduleRequestDto requestDto){
        return scheduleService.updateSchedule(id, password, requestDto);
    }

    @DeleteMapping("/schedules/edit")
    public String deleteSchedule(@RequestParam int id, @RequestParam String password){
        return scheduleService.deleteSchedule(id, password);
    }
}

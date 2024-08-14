package com.example.JavaSpringPrepareAssignment.service;

import com.example.JavaSpringPrepareAssignment.dto.scheduledto.ScheduleRequestDto;
import com.example.JavaSpringPrepareAssignment.dto.scheduledto.ScheduleResponseDto;
import com.example.JavaSpringPrepareAssignment.exception.PasswordMismatchException;
import com.example.JavaSpringPrepareAssignment.exception.DataNotFoundException;
import com.example.JavaSpringPrepareAssignment.entity.Schedule;
import com.example.JavaSpringPrepareAssignment.reopository.schedulerepository.ScheduleRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ResponseEntity<ScheduleResponseDto> create(@RequestBody @Valid ScheduleRequestDto requestDto){
        Schedule schedule = new Schedule(requestDto);
        return ResponseEntity.ok(new ScheduleResponseDto(scheduleRepository.create(schedule)));
    }


    //단건 조회
    public ScheduleResponseDto getSchedule(@PathVariable int id){
        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null){
            return new ScheduleResponseDto(scheduleRepository.findById(id));
        }else{
            throw new DataNotFoundException("선택한 일정은 존재하지 않습니다.");
        }
    }

    //다중 조회
    public List<ScheduleResponseDto> getSchedules(@RequestParam(required = false) int id,
                                                  @RequestParam(required = false) String modificationDate){
        return scheduleRepository.findByIdAndDate(id, modificationDate).stream().map(ScheduleResponseDto::new).toList();
    }

    //페이징 조회
    public List<ScheduleResponseDto> getSchedulesByPaging(@RequestParam int pageNumber, @RequestParam int size){
        return scheduleRepository.findByPaging(pageNumber, size).stream().map(ScheduleResponseDto::new).toList();
    }


    public ScheduleResponseDto updateSchedule(@RequestParam int id, @RequestParam String password, @RequestBody ScheduleRequestDto requestDto){
        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null){
            if(!schedule.getPassword().equals(password)) throw new PasswordMismatchException(id + " 의 " + "패스워드가 올바르지 않습니다.");

            schedule.setToDo(requestDto.getToDo());
            schedule.setManagerId(requestDto.getManagerId());
            return new ScheduleResponseDto(scheduleRepository.update(id, password, schedule));
        }else{
            throw new DataNotFoundException(id + " 의 " + "일정은 존재하지 않습니다.");
        }
    }

    public String deleteSchedule(@RequestParam int id, @RequestParam String password){
        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null){
            if(!schedule.getPassword().equals(password)) throw new IllegalArgumentException("패스워드가 올바르지 않습니다.");

            return scheduleRepository.delete(id, password);
        }else{
            throw new DataNotFoundException("선택한 관리자는 존재하지 않습니다.");
        }
    }

}

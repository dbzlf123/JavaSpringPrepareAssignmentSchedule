package com.example.JavaSpringPrepareAssignment.reopository.schedulerepository;

import com.example.JavaSpringPrepareAssignment.entity.Manager;
import com.example.JavaSpringPrepareAssignment.entity.Schedule;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ScheduleRepository {
    Schedule create(Schedule schedule);
    Schedule findById(int id);
    List<Schedule> findByIdAndDate(int id, String modificationDate);
    List<Schedule> findByPaging(int pageNumber, int size);
    Schedule update(int id, String password, Schedule schedule);
    String delete(int id , String password);
}

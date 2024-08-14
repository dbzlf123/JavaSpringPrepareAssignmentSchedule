package com.example.JavaSpringPrepareAssignment.reopository.schedulerepository;

import com.example.JavaSpringPrepareAssignment.dto.scheduledto.ScheduleResponseDto;
import com.example.JavaSpringPrepareAssignment.entity.Schedule;
import com.example.JavaSpringPrepareAssignment.exception.DataNotFoundException;
import com.example.JavaSpringPrepareAssignment.exception.PasswordMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Schedule create(Schedule schedule) {
        //DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO schedule (ToDo, Password, RegistrationDate, ModificationDate, ManagerID) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, schedule.getToDo());
                    preparedStatement.setString(2, schedule.getPassword());
                    preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf( java.time.LocalDateTime.now()));
                    preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf( java.time.LocalDateTime.now()));
                    preparedStatement.setInt(5, schedule.getManagerId());
                    return preparedStatement;
                },
                keyHolder);

        //Insert 후 받아온 기본키 확인
        int id = keyHolder.getKey().intValue();
        schedule.setScheduleId(id);
        schedule.setRegistrationDate(java.time.LocalDateTime.now());
        schedule.setModificationDate(java.time.LocalDateTime.now());

        //Entity -> ResponseDto
        return schedule;
    }

    @Override
    public Schedule findById(int id) {
        String sql = "SELECT * FROM Schedule WHERE ScheduleID = ?";

        return jdbcTemplate.query(sql, result ->{
            if(result.next()){
                Schedule schedule = new Schedule();
                schedule.setScheduleId(result.getInt("ScheduleID"));
                schedule.setToDo(result.getString("ToDo"));
                schedule.setPassword(result.getString("Password"));
                schedule.setManagerId(result.getInt("ManagerID"));
                schedule.setRegistrationDate(result.getTimestamp("RegistrationDate").toLocalDateTime());
                schedule.setModificationDate(result.getTimestamp("ModificationDate").toLocalDateTime());
                return schedule;
            }else{
                return null;
            }
        } , id);
    }

    @Override
    public List<Schedule> findByIdAndDate(int id, String modificationDate) {
        LocalDate date = LocalDate.parse(modificationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String sql = "SELECT *" +
                " FROM Schedule WHERE ScheduleID = ? OR DATE(ModificationDate) = ? ORDER BY ModificationDate DESC";

        return jdbcTemplate.query(sql, new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                Schedule schedule = new Schedule();
                schedule.setScheduleId(rs.getInt("ScheduleID"));
                schedule.setToDo(rs.getString("ToDo"));
                schedule.setRegistrationDate(rs.getTimestamp("RegistrationDate").toLocalDateTime());
                schedule.setModificationDate(rs.getTimestamp("ModificationDate").toLocalDateTime());
                schedule.setManagerId(rs.getInt("ManagerID"));
                return schedule;
            }
        }, id, date);
    }

    @Override
    public List<Schedule> findByPaging(int pageNumber, int size) {
        String sql = "SELECT * FROM schedule ORDER BY ScheduleID LIMIT ? OFFSET ?";

        int offset = pageNumber * size;

        List<Schedule> schedules = jdbcTemplate.query(sql, new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                Schedule schedule = new Schedule();
                schedule.setScheduleId(rs.getInt("ScheduleID"));
                schedule.setToDo(rs.getString("ToDo"));
                schedule.setRegistrationDate(rs.getTimestamp("RegistrationDate").toLocalDateTime());
                schedule.setModificationDate(rs.getTimestamp("ModificationDate").toLocalDateTime());
                schedule.setManagerId(rs.getInt("ManagerID"));

                String findNameSql = "SELECT Name FROM manager WHERE ManagerID = ?";
                String name = jdbcTemplate.queryForObject(findNameSql, new Object[]{schedule.getManagerId()}, String.class);

                return schedule;
            }
        }, size, offset);

        return schedules;
    }

    @Override
    public Schedule update(int id, String password, Schedule schedule) {
        String sql = "UPDATE schedule SET toDo = ?, managerId = ?, modificationDate = ?  WHERE ScheduleId = ? AND Password = ?";
        jdbcTemplate.update(sql, schedule.getToDo(), schedule.getManagerId(),
                java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()), id, password);
        return schedule;
    }

    @Override
    public String delete(int id, String password) {
        String sql = "DELETE FROM schedule WHERE ScheduleId = ? AND Password = ?";
        jdbcTemplate.update(sql, id, password);

        return "삭제 완료";
    }

}

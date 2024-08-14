package com.example.JavaSpringPrepareAssignment.reopository.managerrepository;

import com.example.JavaSpringPrepareAssignment.dto.managerdto.ManagerResponseDto;
import com.example.JavaSpringPrepareAssignment.entity.Manager;
import com.example.JavaSpringPrepareAssignment.exception.DataNotFoundException;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ManagerRepositoryImpl implements ManagerRepository {

    private final JdbcTemplate jdbcTemplate;

    public ManagerRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Manager create(Manager manager) {
        //DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO manager (name, email, RegistrationDate, ModificationDate) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, manager.getName());
            preparedStatement.setString(2, manager.getEmail());
            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf( java.time.LocalDateTime.now()));
            preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf( java.time.LocalDateTime.now()));
            return preparedStatement;
        }, keyHolder);

        //Insert 후 받아온 기본키 확인
        int id = keyHolder.getKey().intValue();
        manager.setManagerId(id);

        //Entity -> ResponseDto
        return manager;
    }

    @Override
    public Manager findById(int id) {
        String sql = "SELECT * FROM Manager WHERE ManagerId = ?";

        return jdbcTemplate.query(sql, result ->{
            if(result.next()){
                Manager manager = new Manager();
                manager.setManagerId(result.getInt("ManagerID"));
                manager.setName(result.getString("name"));
                manager.setEmail(result.getString("email"));
                manager.setRegistrationDate(result.getTimestamp("RegistrationDate").toLocalDateTime());
                manager.setModificationDate(result.getTimestamp("ModificationDate").toLocalDateTime());
                return manager;
            }else{
                return null;
            }
        } , id);
    }

    @Override
    public List<Manager> findAll() {
        String sql = "SELECT * FROM manager";
        return jdbcTemplate.query(sql, new RowMapper<Manager>() {
            @Override
            public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
                Manager manager = new Manager();
                manager.setManagerId(rs.getInt("ManagerID"));
                manager.setName(rs.getString("Name"));
                manager.setEmail(rs.getString("Email"));
                manager.setRegistrationDate(rs.getTimestamp("RegistrationDate").toLocalDateTime());
                manager.setModificationDate(rs.getTimestamp("ModificationDate").toLocalDateTime());
                return manager;
            }
        });
    }

    @Override
    public Manager update(int id, Manager manager) {
        String sql = "UPDATE Manager SET Name = ?, Email = ?, ModificationDate = ? WHERE ManagerId = ?";
        jdbcTemplate.update(sql, manager.getName(), manager.getEmail(), java.time.LocalDateTime.now(), id);

        return manager;
    }

    @Override
    public String delete(int id) {
        String sql = "DELETE FROM Manager WHERE ManagerId = ?";
        jdbcTemplate.update(sql, id);

        return "삭제 완료";
    }
}

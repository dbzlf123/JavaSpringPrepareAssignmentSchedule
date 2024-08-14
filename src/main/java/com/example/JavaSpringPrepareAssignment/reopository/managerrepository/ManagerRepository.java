package com.example.JavaSpringPrepareAssignment.reopository.managerrepository;

import com.example.JavaSpringPrepareAssignment.entity.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository {
    Manager create(Manager manager);
    Manager findById(int id);
    List<Manager> findAll();
    Manager update(int id, Manager manager);
    String delete(int id);
}

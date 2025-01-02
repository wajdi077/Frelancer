package com.project.wma.repository;

import com.project.wma.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeRepo extends JpaRepository<Employee,UUID> {

    void deleteAllById(List<UUID> uuids);
}

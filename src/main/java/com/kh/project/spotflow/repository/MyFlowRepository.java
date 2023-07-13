package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
@Repository
public interface MyFlowRepository extends JpaRepository<TimeLine, Long> {
    List<TimeLine> findByCustomer(Customer customer);

    List<TimeLine> findTimelineById(Long id);
}


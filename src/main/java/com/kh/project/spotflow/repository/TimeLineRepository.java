package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.Member;
import com.kh.project.spotflow.model.entity.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {
}

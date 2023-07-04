package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.Member;
import com.kh.project.spotflow.model.entity.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TimelineRepository extends JpaRepository<TimeLine, Long> {

    // 이메일로 타임라인 조회
    List<TimeLine> findByEmail(String email);

}

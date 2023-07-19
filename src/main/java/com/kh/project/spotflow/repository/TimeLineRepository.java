package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimeLineRepository extends JpaRepository<TimeLine, Long>, TimeLineCustomRepository {
  TimeLine findTimeLineById(Long id);

  List<TimeLine> findByPlace(String place);

}

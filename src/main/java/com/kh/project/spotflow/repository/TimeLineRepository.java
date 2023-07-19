package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TimeLineRepository extends JpaRepository<TimeLine, Long>, TimeLineCustomRepository {
  // 탐이라인 상세 조회
  TimeLine findTimeLineById(Long id);
  
  // 이거 뭐지?
  List<TimeLine> findTimelineById(Long id);
  
  // 위치 기준의로 탐임라인 가죠오기
  List<TimeLine> findByPlace(String place);
  
  // 이거 필요 없은 근대 확인중
  List<TimeLine> findByCustomer(Customer customer);
}

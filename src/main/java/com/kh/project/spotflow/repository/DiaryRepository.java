package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
  List<Diary> findDiaryByCustomerOrderByJoinDateDesc(Customer customer);
  Diary findDiaryById(Long id);
}

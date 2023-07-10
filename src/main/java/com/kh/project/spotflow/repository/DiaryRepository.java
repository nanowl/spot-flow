package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
  List<Diary> findDiaryByMemberOrderByJoinDateDesc(Member member);
  List<Diary> findDiaryByOrderByLikeDesc();
  Diary findDiaryById(Long id);
}

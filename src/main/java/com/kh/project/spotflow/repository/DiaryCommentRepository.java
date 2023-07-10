package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.DiaryComment;
import com.kh.project.spotflow.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryCommentRepository extends JpaRepository<DiaryComment, Long> {
  DiaryComment findDiaryCommentById(Long Id);
  DiaryComment findDiaryCommentByMember(Member member);
  DiaryComment findDiaryCommentByDiary(Diary diary);
}

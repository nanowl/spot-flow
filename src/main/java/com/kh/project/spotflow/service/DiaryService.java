package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.DiaryRequestDto;
import com.kh.project.spotflow.model.dto.DiaryResponseDto;
import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.DiaryItem;
import com.kh.project.spotflow.model.entity.Member;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.DiaryItemRepository;
import com.kh.project.spotflow.repository.DiaryRepository;
import com.kh.project.spotflow.repository.MemberRepository;
import com.kh.project.spotflow.repository.TimeLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiaryService {
  private final DiaryRepository diaryRepository;
  private final MemberRepository memberRepository;
  private final TimeLineRepository timeLineRepository;
  private final DiaryItemRepository itemRepository;

  public DiaryResponseDto findDiaryById(Long num) {
    Diary diary = diaryRepository.findDiaryById(num);
    List<DiaryItem> itemList = itemRepository.findByDiary(diary);
    DiaryResponseDto responseDto = new DiaryResponseDto().of(diary);
    List<TimeLine> timeLineList = new ArrayList<>();
    for (int i = 0; i < itemList.size(); i++) {
      DiaryItem item = itemList.get(i);
      TimeLine timeLine = timeLineRepository.findTimeLineById(item.getId());
      log.info(timeLine.toString());
      timeLineList.add(timeLine);
    }
    responseDto.setTimeLineList(timeLineList);
    return responseDto;
  }

  public List<Diary> findDiaryByMember(String email) {
    Member member = memberRepository.findMemberByEmail(email);
    return diaryRepository.findDiaryByMember(member);
  }


  public Diary save(DiaryRequestDto requestDiary) {
    Member member = memberRepository.findMemberByEmail(requestDiary.getEmail());
    Diary diary = requestDiary.toDiary();
    List<TimeLine> timeLineList = requestDiary.getTimeLineList();
    List<DiaryItem> itemList = new ArrayList<>();
    diary.setMember(member);

    for (int i = 0; i<timeLineList.size(); i ++) {
      DiaryItem item = DiaryItem.builder()
              .diary(diary)
              .timeLine(timeLineList.get(i))
              .build();
      itemList.add(item);
    }

    diary.setItemList(itemList);

    log.info("Diary 생성");
    diaryRepository.save(diary);

    return diary;
  }
}

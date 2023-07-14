package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.diary.TimeLineRequestDto;
import com.kh.project.spotflow.model.dto.diary.DiaryRequestDto;
import com.kh.project.spotflow.model.dto.diary.DiaryResponseDto;
import com.kh.project.spotflow.model.dto.diary.DiaryUpdateRequest;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.Diary;
import com.kh.project.spotflow.model.entity.DiaryItem;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.CustomerRepository;
import com.kh.project.spotflow.repository.DiaryItemRepository;
import com.kh.project.spotflow.repository.DiaryRepository;
import com.kh.project.spotflow.repository.TimeLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiaryService {
  private final DiaryRepository diaryRepository;
  private final CustomerRepository customerRepository;
  private final TimeLineRepository timeLineRepository;
  private final DiaryItemRepository itemRepository;

  // id 값으로 다이어리와 그 다이어리에 포함된 타임라인 들을 리턴하는 메소드
  public DiaryResponseDto findDiaryById(Long num) {
    Diary diary = diaryRepository.findDiaryById(num); // 다이어리 데이터
    List<DiaryItem> itemList = itemRepository.findByDiary(diary); // 다이어리와 일치하는 매핑 데이터를 가져옴
    DiaryResponseDto responseDto = new DiaryResponseDto().of(diary);
    List<TimeLine> timeLineList = new ArrayList<>();
    // 다이어리에 포함된 타임라인들을 가져옴
    for (int i = 0; i < itemList.size(); i++) {
      DiaryItem item = itemList.get(i);
      TimeLine timeLine = timeLineRepository.findTimeLineById(item.getId());
      log.info(timeLine.toString());
      timeLineList.add(timeLine);
    }
    // 다이어리 정보와 그 다이어리에 포함된 타임라인들을 가져옴
    responseDto.setTimeLineList(timeLineList);
    return responseDto;
  }

//  // user별 다이어리 검색
//  public List<Diary> findDiaryByMember(String email) {
//    Customer customer = customerRepository.findCustomerByEmail(email);
//    return diaryRepository.findDiaryByCustomerOrderByJoinDateDesc(customer);
//  }

  public List<DiaryResponseDto> findDiaryByMember(String email) {
    Customer customer = customerRepository.findCustomerByEmail(email);
    List<Diary> diaries = diaryRepository.findDiaryByCustomerOrderByJoinDateDesc(customer);

    List<DiaryResponseDto> diaryDtoList = new ArrayList<>();
    for (Diary diary : diaries) {
      diaryDtoList.add(DiaryResponseDto.builder()
              .title(diary.getTitle())
              .content(diary.getContent())
              .joinDate(diary.getJoinDate())
              .updateTime(diary.getUpdateTime())
              .like(diary.getLike())
              .view(diary.getView())
              .isDelete(diary.isDelete())
              .timeLineList(diary.getCustomer().getTimeLineList())
              .build());
    }
    return diaryDtoList;
  }




  /*
   * 다이어리 수정
   * 수정할 목록 diaryItem.timeline
   */
  @Transactional
  public DiaryResponseDto update(DiaryUpdateRequest request) {
    // 요청 다이어리
    Diary diary = diaryRepository.findDiaryById(request.getId());
    // 수정 될 다이어리 리스트
    List<DiaryItem> diaryItemList = new ArrayList<>();
    if (diary != null) {
      // 제목 및 글 내용 수정
      diary.setTitle(request.getTitle());
      diary.setContent(request.getContent());
      // 현재 다이어리와 타임라인의 매핑 데이터를 가져옴
      List<DiaryItem> currentItemList = itemRepository.findByDiary(diary);
      for (int i = 0; i < currentItemList.size(); i++) {
        DiaryItem item = currentItemList.get(i);
        item.setTimeLine(
                timeLineRepository.findTimeLineById(
                        request.getTimeLineList()
                                .get(i)
                                .getId()
                )
        );
        diaryItemList.add(item);
      }
      diary.setItemList(diaryItemList);
      diary.setUpdateTime(LocalDateTime.now());
    }
    diaryRepository.save(diary);
    DiaryResponseDto responseDto = new DiaryResponseDto().of(diary);
    responseDto.setItemList(diaryItemList);
    return responseDto;
  }

  public DiaryResponseDto delete(DiaryUpdateRequest request) {
    Diary diary = diaryRepository.findDiaryById(request.getId());
    diary.setDelete(true);
    diaryRepository.save(diary);
    DiaryResponseDto responseDto = new DiaryResponseDto().of(diary);
    return responseDto;
  }

  // 다이어리와 매핑 테이블을 저장
  public Diary save(DiaryRequestDto requestDiary) {
    Customer customer = customerRepository.findCustomerByEmail(requestDiary.getEmail());
    Diary diary = requestDiary.toDiary();
    List<TimeLineRequestDto> timeLineList = requestDiary.getTimeLineList();
    List<DiaryItem> itemList = new ArrayList<>();
    diary.setCustomer(customer);

    for (int i = 0; i < timeLineList.size(); i++) {
      TimeLine timeLine = timeLineRepository
              .findTimeLineById(timeLineList.get(i).getId());
      DiaryItem item = DiaryItem.builder()
              .diary(diary)
              .timeLine(timeLine)
              .build();
      itemList.add(item);
    }

    diary.setItemList(itemList);

    log.info("Diary 생성");
    diaryRepository.save(diary);

    return diary;
  }
}

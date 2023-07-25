package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.ResponseNotification;
import com.kh.project.spotflow.model.dto.diary.DiaryResponseDto;
import com.kh.project.spotflow.model.dto.diary.request.DiaryUpdateRequest;
import com.kh.project.spotflow.model.entity.*;
import com.kh.project.spotflow.repository.CustomerRepository;
import com.kh.project.spotflow.repository.DiaryCommentRepository;
import com.kh.project.spotflow.repository.DiaryRepository;
import com.kh.project.spotflow.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final CustomerRepository customerRepository;


    public List<ResponseNotification> findMyNotice(HttpServletRequest request) {
        Customer customer = customerRepository.findCustomerByEmail(request.getHeader("Authorization")); // 수정 필요
        List<Notification> notificationList = notificationRepository.findNotificationByDiaryWriter(customer.getEmail());

        List<ResponseNotification> responseList = new ArrayList<>();

        for (Notification e : notificationList) {
            ResponseNotification responseNotification = new ResponseNotification().of(e);
            responseList.add(responseNotification);
        }

        return responseList;
    }

//    @Transactional
//    public Notification update(RequestBody request) {
//        Notification notification =
//        // 수정 될 다이어리 리스트
//        List<Notification> notificationList = new ArrayList<>();
//        if (diary != null) {
//            // 제목 및 글 내용 수정
//            diary.setTitle(request.getTitle());
//            diary.setContent(request.getContent());
//            // 현재 다이어리와 타임라인의 매핑 데이터를 가져옴
//            List<DiaryItem> currentItemList = itemRepository.findByDiary(diary);
//            for (int i = 0; i < currentItemList.size(); i++) {
//                DiaryItem item = currentItemList.get(i);
//                item.setTimeLine(
//                        timeLineRepository.findTimeLineById(
//                                request.getTimeLineList()
//                                        .get(i)
//                                        .getId()
//                        )
//                );
//                diaryItemList.add(item);
//            }
//            diary.setItemList(diaryItemList);
//            diary.setUpdateTime(LocalDateTime.now());
//        }
//        diaryRepository.save(diary);
//        DiaryResponseDto responseDto = new DiaryResponseDto().of(diary);
//        responseDto.setItemList(diaryItemList);
//        return responseDto;
//    }
}
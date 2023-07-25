package com.kh.project.spotflow.service;

import com.kh.project.spotflow.config.utils.CookieUtils;
import com.kh.project.spotflow.model.dto.ResponseTimeLine;
import com.kh.project.spotflow.model.dto.TimeLine.TimeLineDto;
import com.kh.project.spotflow.model.dto.TimeLine.TimeLineRequestDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.TimeLine.TimeLineRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

import java.util.*;
import java.util.stream.Collectors;

import static com.kh.project.spotflow.model.entity.QTimeLine.timeLine;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeLineService {
     private final AuthService authService;
     private final JPAQueryFactory queryFactory;
     private final TimeLineRepository timeLineRepository;
     
     // 타임라인 조회 무한 스크롤 기능
     public List<TimeLineDto> getAll(Long lastTimelineId, int limit) {
          List<TimeLine> timeLineList = timeLineRepository.findWithNoOffset(lastTimelineId, limit);
          List<TimeLineDto> timeLineDtoList = new ArrayList<>();
          
          for (TimeLine timeLine : timeLineList) {
               TimeLineDto timeLineDto = new TimeLineDto();
               timeLineDto.setTl_profile_pic(timeLine.getImage());
               timeLineDto.setPlace(timeLine.getPlace());
               timeLineDto.setContent(timeLine.getContent());
               timeLineDto.setView(timeLine.getView());
               timeLineDto.setUpdateTime(timeLine.getJoinDate());
               timeLineDto.setNickName(timeLine.getCustomer().getNickName());
               timeLineDto.setCt_profile_pic(timeLine.getCustomer().getProfilePic());
               timeLineDto.setId(timeLine.getId());
               timeLineDtoList.add(timeLineDto);
          }
          return timeLineDtoList;
     }
     
     // TimeLine 글 쓰기
     public TimeLine createPost(TimeLineRequestDto requestDto) {
          Customer customer = authService.getCustomerByEmail();
          TimeLine timeLine = TimeLine.builder()
            .customer(customer)
            .place(requestDto.getPlace())
            .lat(requestDto.getLat())
            .lng(requestDto.getLng())
            .content(requestDto.getContent())
            .joinDate(LocalDateTime.now())
            .view(0)
            .image(requestDto.getTl_profile_pic())
            .build();
          return timeLineRepository.save(timeLine);
     }
     
     // 타임라인 조회수 증가 서비스
     @Transactional
     public void increaseViewCount(Long id, HttpServletRequest request, HttpServletResponse response) {
          String viewHistory = CookieUtils.getCookieValue(request, "viewHistory");
          Set<String> viewedPosts = new HashSet<>();
          
          if (viewHistory != null && !viewHistory.isEmpty()) {
               viewedPosts.addAll(Arrays.asList(viewHistory.split("\\|"))); // changed from ","
          }
          
          if (!viewedPosts.contains(String.valueOf(id))) {
               TimeLine timeLine = timeLineRepository.findById(id)
                 .orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. " + id));
               timeLine.setView(timeLine.getView() + 1);
               viewedPosts.add(String.valueOf(id));
               
               CookieUtils.addCookie(response, "viewHistory", String.join("|", viewedPosts), 24 * 60 * 60); // 1 day cookie, changed from ","
          }
     }
     
     // 타임라인 검색 서비스
     @Transactional
     public List<TimeLineDto> searchPlace(String place) {
          
          List<TimeLine> result = queryFactory.selectFrom(timeLine)
            .where(timeLine.place.contains(place))
            .fetch();
          
          if (result.isEmpty()) {
               log.info("장소가 존재하지않아");
          }
          
          List<TimeLineDto> responseDtoList = result.stream()
            .map(timeline -> TimeLineDto.builder()
              .ct_profile_pic(timeline.getCustomer().getProfilePic())
              .tl_profile_pic(timeline.getImage())
              .place(timeline.getPlace())
              .nickName(timeline.getCustomer().getNickName())
              .view(timeline.getView())
              .id(timeline.getId())
              .updateTime(timeline.getJoinDate())
              .content(timeline.getContent())
              .build()
            )
            .collect(Collectors.toList());
          
          return responseDtoList;
     }

    public List<ResponseTimeLine> findAll() {
       List<TimeLine> timeLineList = timeLineRepository.findAll();
       List<ResponseTimeLine> responseTimeLineList = new ArrayList<>();
       for (TimeLine timeLine : timeLineList) {
         ResponseTimeLine responseTimeLine = new ResponseTimeLine().of(timeLine);
         responseTimeLineList.add(responseTimeLine);
       }
       return responseTimeLineList;
    }
}

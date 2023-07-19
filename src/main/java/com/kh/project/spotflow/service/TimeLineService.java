package com.kh.project.spotflow.service;

import com.kh.project.spotflow.config.utils.CookieUtils;
import com.kh.project.spotflow.model.dto.TimeLineDto;
import com.kh.project.spotflow.model.dto.TimeLineRequestDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.CustomerRepository;
import com.kh.project.spotflow.repository.TimeLineRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeLineService {







    @Autowired
    private final TimeLineRepository timeLineRepository;

    @Autowired
    private CustomerRepository memberRepository;


    // 타임라인글 전제 조회 하기
    public List<TimeLineDto> findAll() {
        List<TimeLine> timeLineList = timeLineRepository.findAll();
        List<TimeLineDto> timelineDTOS = new ArrayList<>();



        for (TimeLine timeLine : timeLineList) {
            TimeLineDto timelineDTO = new TimeLineDto();
            timelineDTO.setTl_profile_pic(timeLine.getImage());
            timelineDTO.setView(timeLine.getView());
            timelineDTO.setId(timeLine.getId());
            timelineDTO.setUpdateTime(timeLine.getUpdateTime());
            timelineDTO.setPlace(timeLine.getPlace());
            timelineDTOS.add(timelineDTO);
        }
        return timelineDTOS;
    }

    public List<TimeLineDto> getAll(Long lastTimelineId, int limit) {
        List<TimeLine> timeLineList = timeLineRepository.findWithNoOffset(lastTimelineId, limit);
        List<TimeLineDto> timeLineDtoList = new ArrayList<>();

        for(TimeLine timeLine : timeLineList){
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

    public List<TimeLine> createPosts(int count, String userEmail) {
        Customer customer = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("Member does not exist!"));

        List<TimeLine> timeLines = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            TimeLine timeLine = TimeLine.builder()
                    .customer(customer)
                    .place("Some place " + i)
                    .content("Post content " + i)
                    .joinDate(LocalDateTime.now())
                    .view(0)
                    .image("https://images.unsplash.com/photo-1481349518771-20055b2a7b24?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cmFuZG9tfGVufDB8fDB8fHww&w=1000&q=80")
                    .build();
            timeLines.add(timeLine);
            log.info("count : " + i);
        }

        return timeLineRepository.saveAll(timeLines);
    }

    public TimeLine createPost(TimeLineRequestDto request) {
        Customer customer = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("멤버가 존재하지 않습니다.!"));

        TimeLine timeLine = TimeLine.builder()
                .customer(customer)
                .place("역삼동")
                .lat(123.2)
                .lng(112.1)
                .content(request.getContent())
                .joinDate(LocalDateTime.now())
                .view(0)
                .image(request.getTl_profile_pic())
                .build();

        return timeLineRepository.save(timeLine);
    }

    // 타임라인 조회수 증가 서비스
    @Transactional
    public void increaseViewCount(Long id, HttpServletRequest request, HttpServletResponse response) {
        String viewHistory = CookieUtils.getCookieValue(request, "viewHistory");
        Set<String> viewedPosts = new HashSet<>();

        if(viewHistory != null && !viewHistory.isEmpty()) {
            viewedPosts.addAll(Arrays.asList(viewHistory.split("\\|"))); // changed from ","
        }

        if(!viewedPosts.contains(String.valueOf(id))) {
            TimeLine timeLine = timeLineRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. " + id));
            timeLine.setView(timeLine.getView() + 1);
            viewedPosts.add(String.valueOf(id));

            CookieUtils.addCookie(response, "viewHistory", String.join("|", viewedPosts), 24 * 60 * 60); // 1 day cookie, changed from ","
        }
    }

    // 타임라인 검색 서비스
    @Transactional
    public List<TimeLine> searchPlace(String place) {
        List<TimeLine> result = timeLineRepository.findByPlace(place);
        if(result == null) {
            log.info("장소가 존재하지않아");
        }

        List<TimeLine> rs = new ArrayList<>();


        return result;
    }






}

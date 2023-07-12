package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.TimeLineDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.CustomerRepository;
import com.kh.project.spotflow.repository.TimeLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            timelineDTOS.add(timelineDTO);
        }
        return timelineDTOS;
    }

    public List<TimeLineDto> getAll() {
        List<TimeLine> timeLineList = timeLineRepository.findAll();
        List<TimeLineDto> timeLineDtoList = new ArrayList<>();
        for(TimeLine timeLine : timeLineList){
            TimeLineDto timeLineDto = new TimeLineDto();
            timeLineDto.setTl_profile_pic(timeLine.getImage());
            timeLineDto.setContent(timeLine.getContent());
            timeLineDto.setView(timeLine.getView());
            timeLineDto.setUpdateTime(timeLine.getUpdateTime());
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

}

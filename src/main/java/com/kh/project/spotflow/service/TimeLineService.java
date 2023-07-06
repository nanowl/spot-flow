package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.TimeLineDto;
import com.kh.project.spotflow.model.entity.Member;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.MemberRepository;
import com.kh.project.spotflow.repository.TimeLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private MemberRepository memberRepository;


    // 타임라인글 전제 조회 하기
    public List<TimeLineDto> findAll() {
        List<TimeLine> timeLineList = timeLineRepository.findAll();
        List<TimeLineDto> timelineDTOS = new ArrayList<>();
        for (TimeLine timeLine : timeLineList) {
            TimeLineDto timelineDTO = new TimeLineDto();
            timelineDTO.setTl_profile_pic(timeLine.getTl_profile_pic());
            timelineDTO.setView(timeLine.getView());
            timelineDTOS.add(timelineDTO);
        }
        return timelineDTOS;



    }

    public List<TimeLine> createPosts(int count, String userEmail) {
        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("Member does not exist!"));

        List<TimeLine> timeLines = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            TimeLine timeLine = TimeLine.builder()
                    .member(member)
                    .place("Some place " + i)
                    .content("Post content " + i)
                    .joinDate(LocalDateTime.now())
                    .view(0)
                    .title("dfdf")
                    .tl_profile_pic("https://example.com/my_profile_pic.jpg")
                    .build();
            timeLines.add(timeLine);
            log.info("count : " + i);
        }

        return timeLineRepository.saveAll(timeLines);
    }

}

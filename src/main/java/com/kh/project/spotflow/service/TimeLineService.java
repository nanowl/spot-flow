package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.TimeLineDto;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.MemberRepository;
import com.kh.project.spotflow.repository.TimeLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeLineService {
    private final MemberRepository memberRepository;
    private final TimeLineRepository timeLineRepository;

    // 타임라인글 전제 조회 하기
    public List<TimeLineDto> findAll() {
        List<TimeLine> timeLineList = timeLineRepository.findAll();
        List<TimeLineDto> timelineDTOS = new ArrayList<>();
        for (TimeLine timeLine : timeLineList) {
            TimeLineDto timelineDTO = new TimeLineDto();
            timelineDTO.setTitle(timeLine.getTitle());
            timelineDTO.setTl_profile_pic(timeLine.getTl_profile_pic());
            timelineDTO.setLat(timeLine.getLat());
            timelineDTO.setLng(timeLine.getLng());
            timelineDTO.setView(timeLine.getView());
            timelineDTOS.add(timelineDTO);
        }
        return timelineDTOS;
    }
}

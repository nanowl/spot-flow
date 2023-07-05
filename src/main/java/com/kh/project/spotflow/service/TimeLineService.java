package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.TimelineDTO;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.MemberRepository;
import com.kh.project.spotflow.repository.TimeLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeLineService {
    private final MemberRepository memberRepository;
    private final TimeLineRepository timeLineRepository;

    // 타임라인글 전제 조회 하기
    public List<TimelineDTO> findAll() {
        List<TimeLine> timeLineList = timeLineRepository.findAll();
        List<TimelineDTO> timelineDTOS = new ArrayList<>();
        for (TimeLine timeLine : timeLineList) {
            TimelineDTO timelineDTO = new TimelineDTO();
            timelineDTO.setTitle(timeLine.getTitle());
            timelineDTO.setPic(timeLine.getTl_profile_pic());
            timelineDTO.setLat(timeLine.getLat());
            timelineDTO.setLng(timeLine.getLng());
            timelineDTO.setView(timeLine.getView());
            timelineDTOS.add(timelineDTO);
        }
        return timelineDTOS;
    }
}

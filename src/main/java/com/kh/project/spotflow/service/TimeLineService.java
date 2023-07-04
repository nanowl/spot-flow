package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.TimelineDTO;
import com.kh.project.spotflow.model.entity.Member;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.MemberRepository;
import com.kh.project.spotflow.repository.TimelineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TimeLineService {
    private final TimelineRepository timelineRepository;
    private final MemberRepository memberRepository;

    public List<TimelineDTO> getTimelineByEmail(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if (optionalMember.isPresent()) {
            List<TimeLine> timeLineList = timelineRepository.findByEmail(email);
            List<TimelineDTO> timelineDTOS = new ArrayList<>();

            for (TimeLine timeLine : timeLineList) {
                TimelineDTO timelineDTO = new TimelineDTO();
                timelineDTO.setTitle(timeLine.getTitle());
                timelineDTO.setContent(timeLine.getContent());
                timelineDTO.setLng(timeLine.getLng());
                timelineDTO.setLat(timeLine.getLat());
                timelineDTO.setPic(timeLine.getTl_profile_pic());
                timelineDTO.setView(timeLine.getView());

                timelineDTOS.add(timelineDTO);
            }

            return timelineDTOS;
        } else {
            // 처리할 로직 추가 (멤버가 없는 경우)
            return Collections.emptyList();
        }
    }
}
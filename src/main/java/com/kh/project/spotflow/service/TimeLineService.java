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
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeLineService {


    @Autowired
    private final TimeLineRepository timeLineRepository;

    @Autowired
    private MemberRepository memberRepository;


    // 타임라인글 전제 조회 하기
    @Transactional
    public List<TimeLine> findAll() {
        return timeLineRepository.findAll();
    }


    // 타임라인 추가
    @Transactional
    public TimeLine createPost(TimeLineDto request) {
        Member member = memberRepository.findByEmail(request.getUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지않습니다."));

        TimeLineDto timeLineDto = new TimeLineDto();
        timeLineDto.setPlace("Some place"+member.getName());
        timeLineDto.setContent(request.getContent());
        timeLineDto.setTitle(request.getTitle());
        timeLineDto.setUpdateTime(LocalDateTime.now());
        timeLineDto.setView(0);
        timeLineDto.setTl_profile_pic(request.getTl_profile_pic());

        TimeLine timeLine = timeLineDto.toEntity();

        return timeLineRepository.save(timeLine);
    }

}

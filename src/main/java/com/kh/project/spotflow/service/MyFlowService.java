package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.entity.Member;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.MemberRepository;
import com.kh.project.spotflow.repository.MyFlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyFlowService {
    private final MyFlowRepository myFlowRepository;
    private final MemberRepository memberRepository;
    private EntityManager entityManager;

    public Optional<TimeLine> findByMember(Member member) {
        return myFlowRepository.findByMember(member);
    }


        public boolean saveTimeLine(String email, Double lat, Double lng, String content, String img, Long flowId, String place) {
            try {
                Member member = memberRepository.findByEmail(email);
                if (member == null) return false;
                TimeLine timeLine = new TimeLine();
                LocalDateTime localDateTime = LocalDateTime.now();
                member.getTimeLineList().add(timeLine);
                timeLine.setMember(member);
                timeLine.setId(flowId);
                timeLine.setLat(lat);
                timeLine.setLng(lng);
                timeLine.setTl_profile_pic(img);
                timeLine.setContent(content);
                timeLine.setPlace(place);
                timeLine.setJoinDate(localDateTime);
                timeLine.setView(0);

                myFlowRepository.save(timeLine);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
}

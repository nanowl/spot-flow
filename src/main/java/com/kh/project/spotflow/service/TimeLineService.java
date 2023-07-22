package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.TimeLineMyFlowGetDto;
import com.kh.project.spotflow.model.dto.chat.MyFlowRequestDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.TimeLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeLineService {
    private final TimeLineRepository timeLineRepository;
    private final AuthService authService;
    
    //나의 타임라인 글들 가죠오기
    public List<TimeLineMyFlowGetDto> getAll(HttpServletRequest request) {
        Customer customer = authService.getCustomerByEmail();
        log.info(customer.getEmail());
        List<TimeLine> timeLineList = timeLineRepository.findByCustomer(customer);
        List<TimeLineMyFlowGetDto> myFlowGetDtoList = new ArrayList<>();
        for(TimeLine timeLine : timeLineList) {
            TimeLineMyFlowGetDto myFlowGetDto = new TimeLineMyFlowGetDto();
            myFlowGetDto.setId(timeLine.getId());
            myFlowGetDto.setDate(timeLine.getJoinDate());
            myFlowGetDto.setImg(timeLine.getImage());
            myFlowGetDto.setLocation(timeLine.getPlace());
            myFlowGetDto.setLat(timeLine.getLat());
            myFlowGetDto.setLng(timeLine.getLng());
            myFlowGetDto.setContent(timeLine.getContent());

            myFlowGetDtoList.add(myFlowGetDto);
        }
        return myFlowGetDtoList;
    }
    
    //나의 timeLine 상세 정보 보기
    public List<TimeLineMyFlowGetDto> getSelected(HttpServletRequest request, Long id) {
        Customer customer = authService.getCustomerByEmail();
        log.info(customer.getEmail());
        List<TimeLine> timeLineList = timeLineRepository.findTimelineById(id);
        List<TimeLineMyFlowGetDto> myFlowGetDtoList = new ArrayList<>();
        for(TimeLine timeLine : timeLineList) {
            TimeLineMyFlowGetDto myFlowGetDto = new TimeLineMyFlowGetDto();
            myFlowGetDto.setId(timeLine.getId());
            myFlowGetDto.setDate(timeLine.getJoinDate());
            myFlowGetDto.setImg(timeLine.getImage());
            myFlowGetDto.setLocation(timeLine.getPlace());
            myFlowGetDto.setLat(timeLine.getLat());
            myFlowGetDto.setLng(timeLine.getLng());
            myFlowGetDto.setContent(timeLine.getContent());

            myFlowGetDtoList.add(myFlowGetDto);
        }
        return myFlowGetDtoList;

    }
    
    //timeLine 글 쓰기
    public boolean saveTimeLine(HttpServletRequest request, MyFlowRequestDto myFlowRequestDto) {
        try {
            Customer customer = authService.getCustomerByEmail();
            TimeLine timeLine = myFlowRequestDto.toTimeline();
            timeLine.setCustomer(customer);
            
            timeLineRepository.save(timeLine);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

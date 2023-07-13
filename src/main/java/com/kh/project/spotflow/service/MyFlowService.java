package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.dto.MyFlowGetDto;
import com.kh.project.spotflow.model.dto.chat.MyFlowRequestDto;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.MyFlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyFlowService {
    private final MyFlowRepository myFlowRepository;
    private final AuthService authService;
    private EntityManager entityManager;

    public List<MyFlowGetDto> getAll(HttpServletRequest request) {
        Customer customer = authService.validateTokenGetCustomerInfo(request);
        log.info(customer.getEmail());
        List<TimeLine> timeLineList = myFlowRepository.findByCustomer(customer);
        List<MyFlowGetDto> myFlowGetDtoList = new ArrayList<>();
        for(TimeLine timeLine : timeLineList) {
            MyFlowGetDto myFlowGetDto = new MyFlowGetDto();
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
    public List<MyFlowGetDto> getSelected(HttpServletRequest request, Long id) {
        Customer customer = authService.validateTokenGetCustomerInfo(request);
        log.info(customer.getEmail());
        List<TimeLine> timeLineList = myFlowRepository.findTimelineById(id);
        List<MyFlowGetDto> myFlowGetDtoList = new ArrayList<>();
        for(TimeLine timeLine : timeLineList) {
            MyFlowGetDto myFlowGetDto = new MyFlowGetDto();
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
        public boolean saveTimeLine(HttpServletRequest request, MyFlowRequestDto myFlowRequestDto) {
            try {
                Customer customer = authService.validateTokenGetCustomerInfo(request);
                TimeLine timeLine = myFlowRequestDto.toTimeline();
                timeLine.setCustomer(customer);

                myFlowRepository.save(timeLine);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
}

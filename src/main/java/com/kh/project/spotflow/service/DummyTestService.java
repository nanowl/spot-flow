package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.constant.Authority;
import com.kh.project.spotflow.model.constant.OpenStatus;
import com.kh.project.spotflow.model.constant.Theme;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.CustomerRepository;
import com.kh.project.spotflow.repository.TimeLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
- DB 의 테스트를 위해 만들어논 로직들
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DummyTestService {
     private final CustomerRepository customerRepository;
     private final TimeLineRepository timeLineRepository;
     
     public List<Customer> saveDummyUser(int count) {
          List<Customer> customerList = new ArrayList<>();
          for (int i = 0; i < count; i++) {
               Customer customer = Customer.builder()
                 .email("testAccount" + i + ".gmail.com")
                 .password("12345")
                 .authority(Authority.ROLE_USER)
                 .profilePic("https://firebasestorage.googleapis.com/v0/b/spotflow-5475a.appspot.com/o/default_avatar.png?alt=media&token=7ea670df-ff84-4a85-bdb2-41b9a7f6a77a")
                 .nickName("testNickname" + i)
                 .joinDate(LocalDateTime.now())
                 .openStatus(OpenStatus.PUBLIC)
                 .theme(Theme.LIGHT_MODE)
                 .build();
               customerList.add(customer);
          }
          customerRepository.saveAll(customerList);
          return customerList;
     }
     
     public List<TimeLine> saveDummyTimeLine(int count){
          List<TimeLine> timeLines = new ArrayList<>();
          for (int i = 0; i < count; i++) {
               Customer customer = customerRepository.findCustomerByEmail("testAccount" + i + ".gmail.com");
               TimeLine timeLine = TimeLine.builder()
                 .place("홍대")
                 .customer(customer)
                 .image("https://i.namu.wiki/i/yZeKOQ6x8chba-r0OwsmZtUZEsGFGm-WGPAZyDd2b4mrdYypGDuIsavmRomoEo9XRsv0B3NuG8oP_GalDsfmpw.webp")
                 .content("Testing content " + i)
                 .lat(22.12)
                 .lng(22.12)
                 .joinDate(LocalDateTime.now())
                 .view(0)
                 .build();
               timeLines.add(timeLine);
          }
          timeLineRepository.saveAll(timeLines);
          return timeLines;
     }
}

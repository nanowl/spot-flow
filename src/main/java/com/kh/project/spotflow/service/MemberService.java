package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.constant.Authority;
import com.kh.project.spotflow.model.constant.OpenStatus;
import com.kh.project.spotflow.model.constant.Theme;
import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final CustomerRepository repository;

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public List<Customer> saveUser(int count) {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Customer customer = Customer.builder()
                    .email("testAccount" + i + "@gmail.com")
                    .password("1234")
                    .nickName("testAccount" + i)
                    .authority(Authority.ROLE_USER)
                    .joinDate(LocalDateTime.now())
                    .profilePic("https://firebasestorage.googleapis.com/v0/b/spotflow-5475a.appspot.com/o/default_avatar.png?alt=media&token=7ea670df-ff84-4a85-bdb2-41b9a7f6a77a")
                    .openStatus(OpenStatus.PUBLIC)
                    .theme(Theme.LIGHT_MODE)
                    .build();
            customers.add(customer);
            log.info("count : " + i);
        }

        return customers;
    }
}
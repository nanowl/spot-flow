package com.kh.project.spotflow.service;

import com.kh.project.spotflow.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
     private final CustomerRepository customerRepository;
     
}

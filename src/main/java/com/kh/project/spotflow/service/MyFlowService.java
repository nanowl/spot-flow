package com.kh.project.spotflow.service;

import com.kh.project.spotflow.model.entity.TimeLine;
import com.kh.project.spotflow.repository.MyFlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyFlowService {
    private final MyFlowRepository repository;

    public Optional<TimeLine> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public void saveTimeLine(TimeLine timeLine) {
        repository.save(timeLine);
    }
}

package com.kh.project.spotflow.controller;

import com.kh.project.spotflow.service.ApiService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
  private final ApiService apiService;

  @GetMapping("/list")
  @ApiOperation(value = "공공데이터 조회", notes = "전기차 충전소에 대한 각종 실시간 공공데이터를 받아온다.")
  public String getApi(@RequestParam("place") String place) throws IOException {
    log.info(place);
    String jsonData = apiService.getData(place);
    log.info("api 정상처리");
    return jsonData;
  }
}

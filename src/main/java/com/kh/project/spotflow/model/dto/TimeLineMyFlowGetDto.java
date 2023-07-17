package com.kh.project.spotflow.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeLineMyFlowGetDto {
    private Long id;
    private String content;
    private LocalDateTime date;
    private String img;
    private String location;
    private Double lat;
    private Double lng;

}

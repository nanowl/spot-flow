package com.kh.project.spotflow.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimelineDTO {
    private String email;
    private String title;
    private String content;
    private int view;
    private String pic;
    private Double lat;
    private Double lng;
}

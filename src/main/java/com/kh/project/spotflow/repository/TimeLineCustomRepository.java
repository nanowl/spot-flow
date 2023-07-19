package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.TimeLine;

import java.util.List;

public interface TimeLineCustomRepository {
    List<TimeLine> findWithNoOffset(Long lastTimelineId, int limit);
}

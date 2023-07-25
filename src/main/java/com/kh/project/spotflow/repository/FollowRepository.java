package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.Customer;
import com.kh.project.spotflow.model.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
     // 팔로워 수 (follower)
     Long countByFollower(Customer customer);
     
     // 팔로잉 수 (following)
     Long countByFollowing(Customer customer);
}

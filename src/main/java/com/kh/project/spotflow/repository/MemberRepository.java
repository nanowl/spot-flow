package com.kh.project.spotflow.repository;

import com.kh.project.spotflow.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByEmail(String email);
  Member findMemberByEmail(String email);
  boolean existsByEmail(String email);
}

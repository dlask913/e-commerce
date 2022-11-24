package com.example.ecommerce_mono.repository;

import com.example.ecommerce_mono.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByUserId(String userId);

}

package com.example.trady.repository;

import com.example.trady.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 아이디(사용자명)로 회원을 찾는 메서드
    Member findByUsername(String username);
}



//@Repository
//public interface MemberRepository extends JpaRepository<Member, Long> {
//    Member findByUsername(String username); // 메서드 정의
//}

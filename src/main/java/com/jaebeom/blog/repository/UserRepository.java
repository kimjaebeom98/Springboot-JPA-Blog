package com.jaebeom.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaebeom.blog.model.User;


// 자동으로 Bean 등록이 돼서 @Repository 선언 필요 없음
public interface UserRepository extends JpaRepository<User, Integer>{

}

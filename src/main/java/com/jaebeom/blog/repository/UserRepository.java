package com.jaebeom.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jaebeom.blog.model.User;


// 자동으로 Bean 등록이 돼서 @Repository 선언 필요 없음
public interface UserRepository extends JpaRepository<User, Integer>{
	// 방법 1
	// JPA Naming 전략
	// SELECT * FROM user WHERE username = ? AND password = ?;
	// User findByUsernameAndPassword(String username, String password);
	
	// 방법 2
	//	@Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
	//	User login(String username, String password);
	
	Optional<User> findByUsername(String username);
	
}

package com.jaebeom.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 스프링부트 시작시 자동으로 읽어서 MySQL에 테이블이 생성 됨
// @DynamicInsert // insert 시에 null인 필드를 제외시켜줌 즉, role은 default = 'user' 지만 insert시에 값을 안 넣으면 null이 입력됨 그래서 제외 시켜야함
public class User {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따라감
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 1234 => 해쉬(비밀번호 암호화) 대비
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("'user'")
	// RoleType 자료형을 앞에 붙여줌으로써 값의 범위를 USER, ADMIN만 가능하도록 함
	// 근데 DB는 RoleType이라는게 없어서 앞에 @Enumerated를 설정해야함
	@Enumerated(EnumType.STRING)
	private RoleType role; // 나중에 Enum(도메인설정, 즉 값의 범위 설정 ex admin, manager, user)을 쓰는게 좋음. 
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
	
	
}

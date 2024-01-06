package com.jaebeom.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 스프링부트 시작시 자동으로 읽어서 MySQL에 테이블이 생성 됨
public class User {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따라감
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 1234 => 해쉬(비밀번호 암호화) 대비
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'") // '' << 따옴표 주의
	private String role; // 나중에 Enum(도메인설정, 즉 값의 범위 설정 ex admin, manager, user)을 쓰는게 좋음. 
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
	
	
}

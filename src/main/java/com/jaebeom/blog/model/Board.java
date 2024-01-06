package com.jaebeom.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터 
	private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.
	
	@ColumnDefault("0") // 홀 따옴표가 없으니깐 db에는 number값임
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Blog, One = User, board를 불러올 때 user정보 무조건 불러온다(EAGER)
	@JoinColumn(name="userId") // 필드 값은 userId로 만들어지고 연관관계는 ManyToOne
	private User user; // DB는 오브젝트 저장X, 자바는 오브젝트 저장O -> FK 사용
	
	// 만약 JoinColumn(name = "replyId") 이런 식으로 햇으면 board를 하나 select할 때 여러개의 reply 객체를 불러올겨
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아니다 (FK가 아니다) 즉, DB에 컬럼을 만들지말라
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate; // timestamp
}

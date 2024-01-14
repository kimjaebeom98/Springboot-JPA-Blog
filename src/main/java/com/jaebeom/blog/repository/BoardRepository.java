package com.jaebeom.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaebeom.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}

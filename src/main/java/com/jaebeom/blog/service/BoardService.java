package com.jaebeom.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaebeom.blog.model.Board;
import com.jaebeom.blog.model.RoleType;
import com.jaebeom.blog.model.User;
import com.jaebeom.blog.repository.BoardRepository;
import com.jaebeom.blog.repository.UserRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	@Transactional
	public void 글쓰기(Board board ,User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
}

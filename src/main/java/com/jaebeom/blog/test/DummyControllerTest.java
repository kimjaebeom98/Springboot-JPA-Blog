package com.jaebeom.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaebeom.blog.model.RoleType;
import com.jaebeom.blog.model.User;
import com.jaebeom.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입 > 등록된 bean을 찾아 의존성 주입해 줌
	private UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		
		System.out.println(user.getUsername()); 
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입 완료";
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/{없는id}를 찾으면 DB에서 못찾아오게 되면 user객체가 null이 되지 않느냐?
		// 그럼 return null이 리턴이 되어서 프로그램에 문제가 생기겠지..
		// 그래서 Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		// 웹 브라우저에 return하는거는 user 객체 = 자바 오브젝트
		// 웹 브라우저는 html만 읽을 수 있는 자바 오브젝트를 던져주니깐 문제  따라서 웹 브라우저가 이해할 수 잇는 데이터로 변환 필요
		// 스프링부트는 MessageConverter라는 애가 응답시에 자동 작동이 됨
		// 따라서 자바 오브젝트를 리턴하게 되면 MessagerConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져줌
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한 페이지당 2건의 데이터를 리턴 받을 예정
	// /dummy/user?page=0 하면 id가 높은 순 2개를 들고옴 page=1은 그다음 페이지
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
}

package com.jaebeom.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jaebeom.blog.model.RoleType;
import com.jaebeom.blog.model.User;

import lombok.Data;

@Data
public class PrincipalDetail implements UserDetails{
	
	private User user; // 객체를 품고있는 것은 콤포지션, extends는 객체를 사용하는 것
	
	public PrincipalDetail(User user) {
		this.user = user;
	}


	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴 (true: 만료 안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비번이 만료되지 않았는지 리턴 (true: 만료 안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화되지 않았는지 리턴 (true: 만료 안됨)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(simpleGrantedAuthority);
		
		return collectors;
	}
}

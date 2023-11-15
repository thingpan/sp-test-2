package com.sp.file.common.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sp.file.mapper.UserInfoMapper;
import com.sp.file.vo.UserInfoVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoService implements UserDetailsService {
	
	private final UserInfoMapper userMapper;
	
	private final PasswordEncoder passwordEncoder;
	
	public int join(UserInfoVO user) {
		user.setUiPwd(passwordEncoder.encode(user.getUiPwd()));
		return userMapper.insertUserInfo(user);
	}
	
	@Override
	public UserInfoVO loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfoVO login = userMapper.selectUserInfoById(username);
		if(login==null) {
			throw new UsernameNotFoundException("아이디나 비밀번호가 잘못되었습니다");
		}
		return login;
	}

}

package com.sp.file.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sp.file.mapper.UserInfoMapper;
import com.sp.file.vo.UserInfoVO;

public class UserInfoService implements UserDetailsService {
	private UserInfoMapper userInfoMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserInfoVO login = userInfoMapper.selectUserInfoById(username);
		if (login == null) {
			throw new UsernameNotFoundException("아이디나 비밀번호가 잘못되엇습니다");
		}

		return login;
	}
}

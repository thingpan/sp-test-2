package com.sp.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sp.file.common.service.UserInfoService;
import com.sp.file.vo.UserInfoVO;



@Controller
@RestController
public class UserInfoController {
	@Autowired
	 private UserInfoService userService;
	 @PostMapping("/join")
	 public UserInfoVO join(@RequestBody UserInfoVO user) {
		 if(userService.join(user) ==1) {
			 user = userService.loadUserByUsername(user.getUiId());
		 }
		 return user;
	 }
	}


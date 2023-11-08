package com.sp.file.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HtmlController {
	@GetMapping("/html/**")
	public void gopage() {
	}
		@GetMapping("/")
		public String home() {
		return "/html/index";
		}
	
}
	

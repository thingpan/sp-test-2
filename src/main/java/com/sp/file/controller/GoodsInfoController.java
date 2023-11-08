package com.sp.file.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.file.service.GoodsInfoService;
import com.sp.file.vo.GoodsInfoVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GoodsInfoController {
	
	private final GoodsInfoService goodsService;
	
	public GoodsInfoController(@Autowired @Qualifier("goodsInfoServiceImpl") GoodsInfoService goodsService) {
		this.goodsService = goodsService;
	}
	@GetMapping("/goods-infos")
	public List<GoodsInfoVO> getGoodsInfos(GoodsInfoVO goods) {
		return goodsService.selectGoodsInfos(goods);
	}
	@PostMapping("/goods-infos")
	public int addGoodsInfos(GoodsInfoVO goods) {
		log.info("goods=>{}",goods);
		return 1;
	}
}

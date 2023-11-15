package com.sp.file.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/goods-infos/{giNum}")
	public GoodsInfoVO getGoodsInfo(@PathVariable int giNum) {
		return goodsService.selectGoodsInfo(giNum);
	}
	@PostMapping("/goods-infos")
	public int addGoodsInfos(@ModelAttribute GoodsInfoVO goods) {
		log.info("goods=>{}", goods);
		return goodsService.insertGoodsInfo(goods);
	}
	@PatchMapping("/goods-infos")
	public int modifyGoodsInfos(GoodsInfoVO goods) {
		return 1;
	}
}

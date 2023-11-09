package com.sp.file.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.file.mapper.GoodsInfoMapper;
import com.sp.file.service.GoodsFileInfoService;
import com.sp.file.service.GoodsInfoService;
import com.sp.file.vo.GoodsFileInfoVO;
import com.sp.file.vo.GoodsInfoVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class GoodsInfoServiceImpl implements GoodsInfoService {
	
	private GoodsInfoMapper goodsMapper;
	private final GoodsFileInfoService goodsFileService;

	@Override
	public int insertGoodsInfo(GoodsInfoVO goods) {
		int result =goodsMapper.insertGoodsInfo(goods);
		log.info("giNum=>{}",goods.getGiNum());
		result +=goodsFileService.insertGoodsFileInfos(goods.getGiNum() ,goods.getGoodsFiles());
		return goodsMapper.insertGoodsInfo(goods);
	}

	@Override
	public List<GoodsInfoVO> selectGoodsInfos(GoodsInfoVO goods) {
		return goodsMapper.selectGoodsInfos(goods);
	}

	@Override
	public GoodsInfoVO selectGoodsInfo(int giNum) {
		return goodsMapper.selectGoodsInfo(giNum);
	}

}



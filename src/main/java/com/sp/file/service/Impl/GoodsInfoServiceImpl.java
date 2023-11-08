package com.sp.file.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.file.mapper.GoodsInfoMapper;
import com.sp.file.service.GoodsInfoService;
import com.sp.file.vo.GoodsInfoVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GoodsInfoServiceImpl implements GoodsInfoService {
	
	private GoodsInfoMapper goodsMapper;
	
	@Override
	public int insertGoodsInfo(GoodsInfoVO goods) {
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



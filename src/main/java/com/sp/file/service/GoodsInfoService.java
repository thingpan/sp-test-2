package com.sp.file.service;

import java.util.List;

import com.sp.file.vo.GoodsFileInfoVO;
import com.sp.file.vo.GoodsInfoVO;

public interface GoodsInfoService {
	int insertGoodsInfo(GoodsInfoVO goods);
	List<GoodsInfoVO> selectGoodsInfos(GoodsInfoVO goods);
	GoodsInfoVO selectGoodsInfo(int giNum);
}

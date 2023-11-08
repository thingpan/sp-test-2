package com.sp.file.service;

import java.util.List;

import com.sp.file.vo.GoodsFileInfoVO;

public interface GoodsFileInfoService {
	int insertGoodsFileInfo(GoodsFileInfoVO goodsFile);
	List<GoodsFileInfoVO> selectGoodsFileInfos(int giNum);
}

package com.sp.file.service;

import java.io.IOException;
import java.util.List;

import com.sp.file.vo.GoodsFileInfoVO;

public interface GoodsFileInfoService {
	int insertGoodsFileInfo(GoodsFileInfoVO goodsFile);
	int insertGoodsFileInfos(int giNum,List<GoodsFileInfoVO> goodsFiles);
	int updateGoodsFileInfos(int giNum,List<GoodsFileInfoVO> goodsFiles);
	List<GoodsFileInfoVO> selectGoodsFileInfos(int giNum);
}

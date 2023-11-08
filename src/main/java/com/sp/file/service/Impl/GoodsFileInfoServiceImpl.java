package com.sp.file.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.file.mapper.GoodsFileInfoMapper;
import com.sp.file.service.GoodsFileInfoService;
import com.sp.file.vo.GoodsFileInfoVO;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class GoodsFileInfoServiceImpl implements GoodsFileInfoService {

	private final GoodsFileInfoMapper goodsFileMapper; // 이제부터 Autowired 사용 x
	
//	public GoodsFileInfoServiceImpl(@Autowired GoodsFileInfoMapper goodsFileMapper) {
//		this.goodsFileMapper = goodsFileMapper;
//	} => 생성자 주입
	
	@Override
	public int insertGoodsFileInfo(GoodsFileInfoVO goodsFile) {
		return goodsFileMapper.insertGoodsFileInfo(goodsFile);
	}

	@Override
	public List<GoodsFileInfoVO> selectGoodsFileInfos(int giNum) {
		return goodsFileMapper.selectGoodsFileInfos(giNum);
	}

}


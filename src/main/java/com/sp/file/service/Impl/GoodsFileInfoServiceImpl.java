package com.sp.file.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.file.mapper.GoodsFileInfoMapper;
import com.sp.file.service.GoodsFileInfoService;
import com.sp.file.vo.GoodsFileInfoVO;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Primary
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsFileInfoServiceImpl implements GoodsFileInfoService {

	private final GoodsFileInfoMapper goodsFileMapper; // 이제부터 Autowired 사용 x
	@Value("${upload.file-path}")
	private String uploadFilePath;
	
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

	@Override
	public int insertGoodsFileInfos(int giNum,List<GoodsFileInfoVO> goodsFiles) {
		// TODO Auto-generated method stub
		int result =0;
		for(GoodsFileInfoVO goodsFile :goodsFiles) {
			MultipartFile file =goodsFile.getFile();
			String originName =file.getOriginalFilename();
			String extName =originName.substring(originName.lastIndexOf("."));
			String fileName =UUID.randomUUID()+extName;
			goodsFile.setGfiOriginName(originName);
			goodsFile.setGfiPath("/file/"+fileName);
			try {
				file.transferTo(new File(uploadFilePath+fileName));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				log.error("file upload error=>{}",e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("file upload error=>{}",e);
			}
			goodsFile.setGiNum(giNum);
			 result += goodsFileMapper.insertGoodsFileInfo(goodsFile); 
		}
		return result;
	}

}


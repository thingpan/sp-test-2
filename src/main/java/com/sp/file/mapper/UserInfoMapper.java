package com.sp.file.mapper;

import com.sp.file.vo.UserInfoVO;

public interface UserInfoMapper {
 UserInfoVO selectUserInfoById(String uiId);
}

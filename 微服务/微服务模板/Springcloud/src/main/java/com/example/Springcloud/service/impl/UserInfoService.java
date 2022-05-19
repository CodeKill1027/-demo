package com.example.Springcloud.service.impl;

import com.example.Springcloud.dao.UserInfoMapper;
import com.example.Springcloud.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo getUserInfo(String username){
        return userInfoMapper.getUserInfoByUsername(username);
    }
}


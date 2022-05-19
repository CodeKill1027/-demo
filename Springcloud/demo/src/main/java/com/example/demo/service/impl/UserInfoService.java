package com.example.demo.service.impl;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.entity.UserInfo;
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


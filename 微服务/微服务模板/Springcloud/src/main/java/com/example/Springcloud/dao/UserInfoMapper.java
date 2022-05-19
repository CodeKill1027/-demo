package com.example.Springcloud.dao;

import com.example.Springcloud.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoMapper {
    @Select("select * from user where username = #{username}")
    UserInfo getUserInfoByUsername(String username);
}


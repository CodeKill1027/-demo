package com.example.Springcloud.dao;

import com.example.Springcloud.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author yzj
 */
@Mapper
@Repository
public  interface usermapper {

    List<Role> queryRoleall();
    Role queryName(@Param("username") String username);

}




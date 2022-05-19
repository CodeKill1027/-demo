package com.example.security.dao;
import com.example.security.entity.Role;
import com.example.security.entity.SysUser;
import com.example.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @author yzj
 */
@Mapper
@Repository
public  interface usermapper {

  SysUser  accordingToQuerySYUser(@Param("username") String username);
  User accordingToQueryUser(@Param("username") String username);

  Role accordingToNameQueryRole(@Param("username") String username);
}




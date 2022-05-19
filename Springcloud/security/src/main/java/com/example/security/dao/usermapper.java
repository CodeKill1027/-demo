package com.example.security.dao;




import com.example.security.entity.SysUser;
import com.example.security.entity.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * @author yzj
 */
@Mapper
@Repository
public  interface usermapper {

  SysUser  accordingToQuerySYUser(@Param("username") String username);
  user accordingToQueryUser(@Param("username") String username);

}




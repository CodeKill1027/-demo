package com.example.demoVUE.dao;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demoVUE.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author yzj
 */
@Mapper
@Repository
public  interface usermapper {
    List<HashMap<String, Object>> selectall();
}

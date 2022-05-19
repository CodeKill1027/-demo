package com.example.Springcloud.dao;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.Springcloud.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzj
 */
@Mapper
@Repository
public  interface usermapper {
  List<Student> SelectAll();

}




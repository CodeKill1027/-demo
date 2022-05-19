package com.example.demo.dao;


import com.example.demo.entity.Student;
import com.example.demo.entity.Student;
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




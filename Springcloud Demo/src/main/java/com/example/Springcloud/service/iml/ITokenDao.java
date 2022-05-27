package com.example.Springcloud.pojo.service.iml;


import javax.servlet.http.HttpServletRequest;

/**
 * 生成tomken 接口
 * @author Cc
 * @data 2020/4/21 11:57
 */
public interface ITokenDao {

    /**
     * 创建token
     * @return
     */
    String createToken();
    /**
     * 检验token
     * @param request
     * @return
     */
    boolean checkToken(HttpServletRequest request) throws Exception;
}



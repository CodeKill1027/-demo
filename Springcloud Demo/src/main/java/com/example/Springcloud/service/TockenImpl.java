package com.example.Springcloud.service;

import com.alibaba.nacos.common.utils.StringUtils;
import com.example.Springcloud.pojo.service.iml.ITokenDao;
import com.example.Springcloud.util.BaseBusinessException;
import com.example.Springcloud.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class TockenImpl implements ITokenDao {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @return java.lang.String
     * @Description 创建token
     * @param:
     * @author Cc
     * @date 2020/4/21 12:01
     */
    @Override
    public String createToken() {

        String str = UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();
        try {
            token.append("LOGEN_PRES_").append(str);
            redisUtil.setEx(token.toString(), token.toString(), 6L);
            if (StringUtils.isNotEmpty(token.toString())) {
                return token.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param request
     * @return boolean
     * @Description 校验token
     * @param:
     * @author Cc
     * @date 2020/4/21 12:01
     */
    @Override
    public boolean checkToken(HttpServletRequest request) {
        String tokenHeader = request.getHeader("token");
        if (StringUtils.isEmpty(tokenHeader)) {
            throw new BaseBusinessException(500,"无token,请携带token进行请求");
            // header中不存在token
        }
        if (!redisUtil.exists(tokenHeader) ) {
            throw new BaseBusinessException(500,"无效token");
        }
        //删掉请求头token
        boolean remove = redisUtil.remove(tokenHeader);
        if (!remove ) {
            throw new BaseBusinessException(500,"redis 删除Key失败");
        }
        return true;
    }
}

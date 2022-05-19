package com.example.security.service;

import com.example.security.dao.usermapper;


import com.example.security.entity.SysUser;
import com.example.security.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * @author yzj
 */
@Service

public class UserServiceImpl implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
  private  usermapper usermapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String authorization = request.getHeader("login");
         final String sys ="SysUser";
        final String us ="User";
        if (sys.equals(authorization)){
            SysUser sysUser = usermapper.accordingToQuerySYUser(username);
            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
            return sysUser;
   }
        if (us.equals(authorization)){
            user user = usermapper.accordingToQueryUser(username);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return user;
        }
        return null;
    }
}


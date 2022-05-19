package com.example.security.service;

import com.example.security.dao.usermapper;
import com.example.security.entity.Role;
import com.example.security.entity.SysUser;
import com.example.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;


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
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        String authorization = request.getHeader("login");
         final String sys ="SysUser";
        final String us ="User";
        if (sys.equals(authorization)){
            SysUser sysUser = usermapper.accordingToQuerySYUser(username);
            Role role = usermapper.accordingToNameQueryRole(username);
            //封装角色姓名查询权限信息权限
             Set<String> roleName = null;
            assert false;
            roleName.add(role.getPermissions1());
            roleName.add(role.getPermissions2());
            roleName.add(role.getPermissions3());
            sysUser.setRoleName(roleName);
            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
            return sysUser;
   }
        if (us.equals(authorization)){
            User user = usermapper.accordingToQueryUser(username);
            //
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return user;
        }
        return null;
    }
}


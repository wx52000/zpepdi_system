package com.zpepdi.oauth_server.myToken;

import com.zpepdi.oauth_server.entity.User;
import com.zpepdi.oauth_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "kiteUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询数据库操作
        User user = userService.user(username);
        if (user != null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getPosition()));
            String password = passwordEncoder.encode(user.getPassword());
//            String password = passwordEncoder.encode("1234");
//            return new org.springframework.security.core.userdetails.User(username,password, authorities);
            MyUserDetails userDetails = new MyUserDetails();
            userDetails.setUserId(user.getId());
            userDetails.setUsername(username);
            userDetails.setPassword(password);
            userDetails.setRoleId(user.getRoleId());
            userDetails.setAuthorities(authorities);
            return userDetails;
        }else {
             throw new UsernameNotFoundException("Could not find " + username);
        }
            // 用户角色也应在数据库中获取
//            String role = "ROLE_ADMIN";
//            authorities.add(new SimpleGrantedAuthority(role));
//            // 线上环境应该通过用户名查询数据库获取加密后的密码
//            String password = passwordEncoder.encode("123456");
    }
}

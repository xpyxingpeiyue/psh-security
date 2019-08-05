package com.psh.security.browser.service;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.psh.security.browser.bean.User;
import com.psh.security.browser.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by peiyue.xing on 2019/8/5 16:14
 * 自定义登录认证校验逻辑  必须实现UserDetailsService接口
 *
 *  SpringSecurity 密码逻辑
 * org.springframework.security.crypto.password.PasswordEncoder
 * encode : 用于密码加密的
 * matches : 用于判断加密的密码与传输的密码是否匹配
 * @author peiyue.xing
 */
@Component
public class MyUserDetailService implements UserDetailsService {
    private static final Log log = LogFactory.get();
    @Autowired
    private UserRepository userRepository; //连接数据库获取用户

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户登录：{}", username);
        User user = new User();
        user.setUsername(username);
        User u = userRepository.findOne(Example.of(user));
        //org.springframework.security.core.userdetails.User SpringSecurity自己默认的User  实现UserDetails
        //SpringSecurity自己进行密码验证
        return new org.springframework.security.core.userdetails.User(username
                , u.getPassword()
                ,true  //enabled 是否删除 这些逻辑可以自己根据业务实现处理
                ,true //accountNonExpired  用户是否过期
                ,true //credentialsNonExpired session是否过期
                ,true//accountNonLocked 是否锁定
                , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}

package com.mall.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.model.TbSeller;
import com.mall.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomUserService implements UserDetailsService { //1

    @Reference
    SellerService sellerService;

    @Override
    public UserDetails loadUserByUsername(String username) { //2

        //构建角色列表
        List<GrantedAuthority> grantAuths = new ArrayList();
        grantAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        //得到商家对象
        TbSeller seller = sellerService.findOne(username);
        if (seller != null) {
            if (seller.getStatus().equals("1")) {
                return new User(username, seller.getPassword(), grantAuths);
            } else {
                return null;
            }
        } else {
            throw new UsernameNotFoundException("用户名不存在");
        }
    }

}

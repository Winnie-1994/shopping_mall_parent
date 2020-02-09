package com.mall.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Winnie
 * @date :   2019/10/5
 * @description :
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("name")
    public Map name() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map = new HashMap();
        map.put("loginName", name);
        return map;
    }

}


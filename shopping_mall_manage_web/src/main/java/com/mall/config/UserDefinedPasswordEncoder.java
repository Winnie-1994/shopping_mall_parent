package com.mall.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author : Winnie
 * @date :   2019/9/21
 * @description :
 */
public class UserDefinedPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}

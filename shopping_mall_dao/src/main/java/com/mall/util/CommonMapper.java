package com.mall.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author : Winnie
 * @date :   2019/9/10
 * @description :
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
}

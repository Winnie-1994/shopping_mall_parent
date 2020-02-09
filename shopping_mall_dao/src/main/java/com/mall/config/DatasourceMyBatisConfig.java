package com.mall.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @author : Vander
 * @date : 2018-08-14
 * @description ：
 */
@Configuration
@MapperScan("com.mall.mapper")
public class DatasourceMyBatisConfig {

}

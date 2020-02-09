package com.mall.mapper;

import com.mall.model.TbBrand;
import com.mall.util.CommonMapper;

import java.util.List;
import java.util.Map;

public interface TbBrandMapper extends CommonMapper<TbBrand> {

    /**
     * 获取品牌信息
     *
     * @return
     */
    List<Map> selectOptionList();

}
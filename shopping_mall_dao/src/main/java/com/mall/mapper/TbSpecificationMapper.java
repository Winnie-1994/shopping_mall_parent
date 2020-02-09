package com.mall.mapper;

import com.mall.model.TbSpecification;
import com.mall.util.CommonMapper;

import java.util.List;
import java.util.Map;

public interface TbSpecificationMapper extends CommonMapper<TbSpecification> {

    /**
     * 获取所有规格列表
     * @return
     */
    List<Map> selectOptionList();

}
package com.mall.sellergoods.service;

import java.util.List;

import com.mall.entity.PageResult;
import com.mall.model.TbGoods;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface GoodsService {

    /**
     * 返回全部列表
     *
     * @return
     */
    List<TbGoods> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
//	void add(Goods goods);


    /**
     * 修改
     */
    void update(TbGoods goods);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    TbGoods findOne(Long id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 分页
     *
     * @param pageNum  当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    PageResult findPage(TbGoods goods, int pageNum, int pageSize);

}

package com.mall.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.entity.PageResult;
import com.mall.mapper.TbSpecificationMapper;
import com.mall.mapper.TbSpecificationOptionMapper;
import com.mall.model.TbSpecification;
import com.mall.model.TbSpecificationOption;
import com.mall.modelgroup.Specification;
import com.mall.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 业务逻辑实现
 * @author Steven
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.select(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		
		PageResult<TbSpecification> result = new PageResult<TbSpecification>();
        //设置分页条件
        PageHelper.startPage(pageNum, pageSize);

        //查询数据
        List<TbSpecification> list = specificationMapper.select(null);
        //保存数据列表
        result.setRows(list);

        //获取总记录数
        PageInfo<TbSpecification> info = new PageInfo<TbSpecification>(list);
        result.setTotal(info.getTotal());
		return result;
	}

	@Override
	public void add(Specification specification) {
		specificationMapper.insert(specification.getSpecification());//插入规格
		//循环插入规格选项
		for(TbSpecificationOption specificationOption : specification.getSpecificationOptionList()){
			//设置规格ID
			specificationOption.setSpecId(specification.getSpecification().getId());
			specificationOptionMapper.insert(specificationOption);
		}
	}

//	/**
//	 * 增加
//	 */
//	@Override
//	public void add(Specification specification) {
//		//保存规格名称信息
//		specificationMapper.insertSelective(specification.getSpecification());
//
//		//保存规格选项列表
//		for (TbSpecificationOption option : specification.getSpecificationOptionList()) {
//			//绑定主表Id
//			option.setSpecId(specification.getSpecification().getId());
//			optionMapper.insertSelective(option);
//		}
//	}
//
//
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
		//更新规格名称信息
		specificationMapper.updateByPrimaryKeySelective(specification.getSpecification());

		//更新选项列表
		//先删除所有原来的列表
		TbSpecificationOption where = new TbSpecificationOption();
		where.setSpecId(specification.getSpecification().getId());
		specificationOptionMapper.delete(where);

		//保存新列表
		//保存规格选项列表
		for (TbSpecificationOption option : specification.getSpecificationOptionList()) {
			//绑定主表Id
			option.setSpecId(specification.getSpecification().getId());
			specificationOptionMapper.insertSelective(option);
		}
	}

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		Specification specification = new Specification();
		//查询规格名称信息
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		specification.setSpecification(tbSpecification);
		//查询选项列表
		TbSpecificationOption where = new TbSpecificationOption();
		where.setSpecId(id);
		List<TbSpecificationOption> options = specificationOptionMapper.select(where);
		specification.setSpecificationOptionList(options);

		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		//数组转list
        List longs = Arrays.asList(ids);
        //构建查询条件
        Example example = new Example(TbSpecification.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", longs);

        //跟据查询条件删除数据
        specificationMapper.deleteByExample(example);

        //删除选项列表
		Example example2 = new Example(TbSpecificationOption.class);
		Example.Criteria criteria2 = example2.createCriteria();
		criteria2.andIn("specId", longs);
		specificationOptionMapper.deleteByExample(example2);
	}
	
	
	@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageResult<TbSpecification> result = new PageResult<TbSpecification>();
        //设置分页条件
        PageHelper.startPage(pageNum, pageSize);

        //构建查询条件
        Example example = new Example(TbSpecification.class);
        Example.Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						//如果字段不为空
			if (specification.getSpecName()!=null && specification.getSpecName().length()>0) {
				criteria.andLike("specName", "%" + specification.getSpecName() + "%");
			}
	
		}

        //查询数据
        List<TbSpecification> list = specificationMapper.selectByExample(example);
        //保存数据列表
        result.setRows(list);

        //获取总记录数
        PageInfo<TbSpecification> info = new PageInfo<TbSpecification>(list);
        result.setTotal(info.getTotal());
		
		return result;
	}

	@Override
	public List<Map> selectOptionList() {
		return specificationMapper.selectOptionList();
	}

}

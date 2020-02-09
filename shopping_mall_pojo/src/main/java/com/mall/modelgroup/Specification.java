package com.mall.modelgroup;

import com.mall.model.TbSpecification;
import com.mall.model.TbSpecificationOption;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 规格组合实体类 
 * @author Administrator
 *
 */
@Getter
@Setter
public class Specification implements Serializable {

	private TbSpecification specification;

	private List<TbSpecificationOption> specificationOptionList;

}

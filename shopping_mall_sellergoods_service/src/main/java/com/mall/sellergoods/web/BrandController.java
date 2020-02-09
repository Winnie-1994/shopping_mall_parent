package com.mall.sellergoods.web;

import com.mall.mapper.TbBrandMapper;
import com.mall.model.TbBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Winnie
 * @date :   2019/9/8
 * @description :
 */
@RestController
public class BrandController {

    @Autowired
    private TbBrandMapper brandMapper;

    @GetMapping("findById")
    public String getBrand(@RequestParam long id) {
        TbBrand tbBrands = brandMapper.selectByPrimaryKey(id);
        return tbBrands.toString();
    }

    @GetMapping("findAll")
    public String getAllBrands(){
        List<TbBrand> tbBrandList = brandMapper.select(null);
        return tbBrandList.toString();
    }

}

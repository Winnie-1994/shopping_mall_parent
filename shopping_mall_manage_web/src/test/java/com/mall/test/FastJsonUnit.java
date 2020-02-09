package com.mall.test;

/**
 * @author : Winnie
 * @date :   2019/9/15
 * @description :
 */

import com.alibaba.fastjson.JSONObject;
import com.mall.model.TbTypeTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FastJsonUnit {

//    @Autowired
//    private ObjectMapper objectMapper;

    private static final String json = "{\"customAttributeItems\":[],\"name\":\"笔记本\",\"brandIds\":[{\"id\":3,\"text\":\"三星\"}]}";

//    @Test
//    public void testParse() {
//
//        TbTypeTemplate typeTemplate = null;
//        try {
//            typeTemplate = objectMapper.readValue(json, TbTypeTemplate.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(typeTemplate);
//    }

    @Test
    public void testFastJsonParse() {
        TbTypeTemplate tbTypeTemplate = JSONObject.parseObject(json, TbTypeTemplate.class);
        System.out.println(tbTypeTemplate.getBrandIds());
    }

}

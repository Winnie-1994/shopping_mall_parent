package com.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Winnie
 * @date :   2019/9/8
 * @description :
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    /**
     * 总记录数
     */
    private long total;
    /**
     * 对应页的行数
     */
    private List rows;
}

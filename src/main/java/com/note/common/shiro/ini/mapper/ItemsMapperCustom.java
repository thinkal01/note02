package com.note.common.shiro.ini.mapper;

import com.note.common.shiro.ini.po.Items;
import com.note.common.shiro.ini.po.QueryVo;

import java.util.List;

public interface ItemsMapperCustom {
    //商品列表
    List<Items> findItemsList(QueryVo queryVo) throws Exception;

    //根据id查询商品信息
    Items findItemById(int id) throws Exception;
}

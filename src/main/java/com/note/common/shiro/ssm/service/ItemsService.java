package com.note.common.shiro.ssm.service;

import com.note.common.shiro.ini.po.Items;
import com.note.common.shiro.ini.po.ItemsCustom;
import com.note.common.shiro.ini.po.QueryVo;

import java.util.List;

/**
 * <p>Title: ItemsService</p>
 * <p>Description:商品service接口 </p>
 * <p>Company: www.itcast.com</p>
 *
 * @author 传智.燕青
 * @version 1.0
 * @date 2015-3-20下午3:02:15
 */
public interface ItemsService {

    //删除商品信息
    void deleleItems(Integer id) throws Exception;

    //商品查询列表
    List<Items> findItemsList(QueryVo itemsQueryVo) throws Exception;

    //根据商品id查询商品信息
    ItemsCustom findItemsById(int id) throws Exception;

    //更新商品信息

    /**
     * 定义service接口，遵循单一职责，将业务参数细化 （不要使用包装类型，比如map）
     * <p>Title: updateItems</p>
     * <p>Description: </p>
     *
     * @param id          修改商品的id
     * @param itemsCustom 修改商品的信息
     * @throws Exception
     */
    void updateItems(Integer id, Items itemsCustom) throws Exception;

}

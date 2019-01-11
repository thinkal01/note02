package com.note.common.shiro.ini.mapper;

import com.note.common.shiro.ini.po.SysPermission;

import java.util.List;

public interface SysPermissionMapperCustom {
    //根据用户id获取权限菜单
    List<SysPermission> findMenuByUserid(String userid) throws Exception;

    //根据用户id获取权限
    List<SysPermission> findPermissionByUserid(String userid) throws Exception;
}
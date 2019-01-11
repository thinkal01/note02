package com.note.common.shiro.ini.service;

import com.note.common.shiro.ini.po.ActiveUser;
import com.note.common.shiro.ini.po.SysPermission;

import java.util.List;

public interface SysService {

    //根据用户id获取权限
    List<SysPermission> findSysPermissionList(String userid) throws Exception;

    /**
     * <p>Description:用户认证 </p>
     *
     * @param usercode 用户账号
     * @param password 用户密码
     * @return ActiveUser 用户身份信息
     * @throws Exception
     */
    ActiveUser authenticat(String usercode, String password) throws Exception;
}

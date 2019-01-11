package com.note.common.shiro.ssm.controller;

import com.note.common.shiro.ssm.shiro.CustomRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Description: 手动调用controller清除shiro的缓存</p>
 */
@Controller
public class ClearShiroCache {
    // 注入realm
    @Autowired
    private CustomRealm customRealm;

    @RequestMapping("/clearShiroCache")
    public String clearShiroCache() {
        // 清除缓存，将来正常开发要在service调用customRealm.clearCached()
        customRealm.clearCached();
        return "success";
    }

}

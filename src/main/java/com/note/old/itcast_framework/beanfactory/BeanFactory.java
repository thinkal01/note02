package com.note.old.itcast_framework.beanfactory;

import com.note.old.itcast_framework.beanfactory.cfg.BeanConfig;
import com.note.old.itcast_framework.beanfactory.cfg.PropertyConfig;
import com.note.old.itcast_framework.beanfactory.factorybean.FactoryBean;
import com.note.old.itcast_framework.beanfactory.utils.BeanFactoryUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    // 配置文件的对应体
    private Map<String, BeanConfig> bcs = new HashMap<>();
    // Bean缓存，id是键, Bean是值
    private Map<String, Object> beanCache = new HashMap<>();

    public BeanFactory(String xmlName) {
        BeanFactoryUtils.load(this, xmlName);
    }

    // 如果缓存中存在，直接返回
    // 如果不存在，创建Bean，放入缓存中，再返回
    public Object getBean(String id) {
        BeanConfig bc = bcs.get(id);
        if (bc == null) {
            throw new RuntimeException(id + "不存在！");
        }
        if (bc.getScope() == null || bc.getScope().equals("singleton")) {
            // 如果是单例bean，查看缓存中是否存在，如果存在直接返回
            if (beanCache.containsKey(id)) {
                return beanCache.get(id);
            }
            // 如果缓存中不存在，那么创建之，然后放入缓存中，再返回
            Object bean = createBean(id);
            beanCache.put(id, bean);
            return bean;
        } else if (bc.getScope().equals("prototype")) {
            // 如果是原型bean，那么直接创建，然后返回，不用向入缓存
            Object bean = createBean(id);
            return bean;
        }
        throw new RuntimeException("scope只能是singleton或prototype");
    }


    private Object createBean(String id) {
        try {
            BeanConfig bc = bcs.get(id);//获取Bean配置对象
            Class c = Class.forName(bc.getClassName());
            Object bean = c.newInstance();
            Map<String, PropertyConfig> pcs = bc.getPropertyConfigMap();
            // 遍历所有的PropertyConfig
            for (String propName : pcs.keySet()) {
                PropertyConfig pc = pcs.get(propName);
                if (pc.getRef() != null) {
                    String ref = pc.getRef();//是不是另一个bean的id
                    Object refBean = getBean(ref);
                    BeanUtils.setProperty(bean, pc.getName(), refBean);
                } else {
                    BeanUtils.setProperty(bean, pc.getName(), pc.getValue());
                }
            }
            if (bean instanceof FactoryBean) {
                FactoryBean factoryBean = (FactoryBean) bean;
                return factoryBean.getObject();
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addBeanConfig(BeanConfig bc) {
        bcs.put(bc.getId(), bc);
    }

    public BeanConfig getBeanConfig(String id) {
        return bcs.get(id);
    }

    public Map<String, BeanConfig> getBcs() {
        return bcs;
    }

    public void setBcs(Map<String, BeanConfig> bcs) {
        this.bcs = bcs;
    }


}

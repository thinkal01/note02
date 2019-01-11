package com.note.old.itcast_framework.beanfactory.cfg;

import java.util.LinkedHashMap;
import java.util.Map;

public class BeanConfig {
    private String id;
    private String className;
    private String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        if (!scope.equals("singleton") && !scope.equals("prototype")) {
            throw new RuntimeException("scope只能是singleton或prototype");
        }
        this.scope = scope;
    }

    private Map<String, PropertyConfig> propertyConfigMap = new LinkedHashMap<>();

    // 添加proertyConfig
    // propertyConfig.getName()为键, propertyConfig本身是值
    public void addPropertyConfig(PropertyConfig propertyConfig) {
        propertyConfigMap.put(propertyConfig.getName(), propertyConfig);
    }

    public PropertyConfig getPropertyConfig(String name) {
        return propertyConfigMap.get(name);
    }

    public Map<String, PropertyConfig> getPropertyConfigMap() {
        return propertyConfigMap;
    }

    public void setPropertyConfigMap(Map<String, PropertyConfig> propertyConfigMap) {
        this.propertyConfigMap = propertyConfigMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public BeanConfig() {
        super();
    }

    public BeanConfig(String id, String className) {
        super();
        this.id = id;
        this.className = className;
    }

    @Override
    public String toString() {
        return "BeanConfig [id=" + id + ", className=" + className
                + ", propertyConfigMap=" + propertyConfigMap + "]";
    }
}

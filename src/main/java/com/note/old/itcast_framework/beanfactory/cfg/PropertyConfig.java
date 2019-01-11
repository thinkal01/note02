package com.note.old.itcast_framework.beanfactory.cfg;

public class PropertyConfig {
    private String name;
    private String value;
    private String ref;

    @Override
    public String toString() {
        return "PropertyConfig [name=" + name + ", value=" + value + ", ref="
                + ref + "]";
    }

    public PropertyConfig(String name, String value, String ref) {
        super();
        this.name = name;
        this.value = value;
        this.ref = ref;
    }

    public PropertyConfig() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}

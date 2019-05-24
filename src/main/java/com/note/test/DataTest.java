package com.note.test;

import org.junit.Test;

/**
 * spring boot @Data注解反编译结果
 */
public class DataTest {
    private String id;
    private String name;

    public DataTest() {
    }

    public static DataTest build(String id, String name) {
        DataTest dataTest = new DataTest();
        dataTest.setId(id);
        dataTest.setName(name);
        return dataTest;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof DataTest)) {
            return false;
        } else {
            DataTest other = (DataTest) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof DataTest;
    }

    public int hashCode() {
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "DataTest(id=" + this.getId() + ", name=" + this.getName() + ")";
    }

    @Test
    public void test() {
        DataTest dataTest1 = DataTest.build("123", "456");
        DataTest dataTest2 = DataTest.build("123", "456");
        // 以下相等
        System.out.println(dataTest1.hashCode());
        System.out.println(dataTest2.hashCode());
        // true
        System.out.println(dataTest1.equals(dataTest2));
    }
}

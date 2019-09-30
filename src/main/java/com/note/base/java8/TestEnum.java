package com.note.base.java8;

import lombok.Getter;

@Getter
// 枚举本身是单例的，一般用于项目中定义常量。
enum UserEnum {
    HTTP_200(200, "请求成功"), HTTP_500(500, "请求失败");
    private Integer code;
    private String name;

    UserEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}

public class TestEnum {
    public static void main(String[] args) {
        System.out.println(UserEnum.HTTP_500.getCode());
    }
}
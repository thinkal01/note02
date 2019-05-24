package com.note.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义响应结构
 */
public class AppResult implements Serializable {

    // 定义jackson对象
    public static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public AppResult() {
    }

    public AppResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public AppResult(Object data) {
        this.status = 200;
        this.msg = "操作成功";
        this.data = data;
    }

    public static AppResult build(Integer status, String msg, Object data) {
        return new AppResult(status, msg, data);
    }

    public static AppResult ok(Object data) {
        return new AppResult(data);
    }

    public static AppResult ok() {
        return new AppResult(null);
    }

    public static AppResult build(Integer status, String msg) {
        return new AppResult(status, msg, null);
    }

    public static AppResult build(Integer status, Object data) {
        return new AppResult(status, null, data);
    }

    // 忽略字段
    @JsonIgnore
    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为Result对象
     *
     * @param jsonData json数据
     * @param clazz Result中的object类型
     * @return
     */
    public static AppResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, AppResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg")
                    .asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static AppResult format(String json) {
        try {
            return MAPPER.readValue(json, AppResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static AppResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory()
                        .constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg")
                    .asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        try {
            String toString = MAPPER.writeValueAsString(this);
            return toString;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * 获取泛型的Collection Type
     * @param collectionClass 泛型的Collection
     * @param elementClasses 元素类
     * @return JavaType Java类型
     */
    public static JavaType getCollectionType(Class<?> collectionClass,
                                             Class<?>... elementClasses) {
        return MAPPER.getTypeFactory().constructParametricType(collectionClass,
                elementClasses);
    }

    public static void main(String[] args) {
        //		AppResult format = format("{\"status\": 200,\"msg\": \"OK\",\"data\": \"346E8E3F563640AA8E27615A9958DE5E\"}");
        AppResult format = format("{\"status\": 200,\"msg\": \"OK\",\"data\": null}");
        System.out.println(format.getData());
        System.out.println(format);
    }
}
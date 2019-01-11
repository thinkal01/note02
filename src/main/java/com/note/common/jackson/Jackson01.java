package com.note.common.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Jackson01 {
    // 定义jackson对象
    public static final ObjectMapper mapper = new CustomObjectMapper();

    // JAVA对象转JSON[JSON序列化]
    @Test
    public void test01() throws Exception {
        User user = new User();
        user.setName("zhangsan");
        user.setEmail("zhangsan@163.com");
        user.setAge(20);
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(dateformat.parse("1996-10-01"));

        /**
         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
         * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
         * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
         * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
         * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
         * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
         */
        // 添加功能，让时间格式更具有可读性
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(dateFormat);
        // 使JSON视觉上的可读，注意，在生产中不需要这样，因为这样会增大Json的内容
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        // 配置mapper忽略空属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        //User类转JSON
        //输出结果：{"name":"zhangsan","age":20,"birthday":844099200000,"email":"zhangsan@163.com"}
        String json = mapper.writeValueAsString(user);

        //Java集合转JSON
        //输出结果：[{"name":"zhangsan","age":20,"birthday":844099200000,"email":"zhangsan@163.com"}]
        List<User> users = new ArrayList<>();
        users.add(user);
        String jsonlist = mapper.writeValueAsString(users);
    }

    // JSON转Java类[JSON反序列化]
    public void test02() throws IOException {
        String json = "{\"name\":\"zhangsan\",\"age\":20,\"birthday\":844099200000,\"email\":\"zhangsan@163.com\"}";
        /**
         * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
         */
        //当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
        //因为，例如json里有10个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        User user = mapper.readValue(json, User.class);

        String jsonList = "[{\"name\":\"zhangsan\",\"age\":20,\"birthday\":844099200000,\"email\":\"zhangsan@163.com\"}]";
        List<User> beanList = mapper.readValue(jsonList, new TypeReference<List<User>>() {
        });

        JavaType javaType = getCollectionType(ArrayList.class, User.class);
        List<User> userList = mapper.readValue(jsonList, javaType);
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}

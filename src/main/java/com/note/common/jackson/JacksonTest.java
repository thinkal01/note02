package com.note.common.jackson;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * 将java对象转换成JSON字符串，也可以将JSON字符串转换成java对象
 * jar-lib-version: jackson-all-1.6.2
 */
public class JacksonTest {
    private JsonGenerator jsonGenerator = null;
    private ObjectMapper objectMapper = null;
    private AccountBean bean = null;

    @Before
    public void init() {
        bean = new AccountBean();
        bean.setId(1);
        bean.setName("张三");
        bean.setAddress("china-Guangzhou");
        bean.setEmail("hoojo_@126.com");

        objectMapper = new ObjectMapper();
        try {
            /*分别利用JsonGenerator的writeObject方法和ObjectMapper的writeValue方法完成对Java对象的转换，二者传递的参数及构造的方式不同；
            JsonGenerator的创建依赖于ObjectMapper对象。但用ObjectMapper来转换JSON，则不需要JSONGenerator。
            objectMapper的writeValue方法可以将一个Java对象转换成JSON。*/
            jsonGenerator = objectMapper.getFactory().createGenerator(System.out, JsonEncoding.UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void destory() {
        try {
            if (jsonGenerator != null) {
                jsonGenerator.flush();
            }
            if (!jsonGenerator.isClosed()) {
                jsonGenerator.close();
            }
            jsonGenerator = null;
            objectMapper = null;
            bean = null;
            System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeEntityJSON() {
        try {
            System.out.println("jsonGenerator");
            //writeObject可以转换java对象，eg:JavaBean/Map/List/Array等
            jsonGenerator.writeObject(bean);

            System.out.println();

            /*这个方法的参数一，需要提供一个输出流，转换后可以通过这个流来输出转换后的内容。或是提供一个File，将转换后的内容写入到File中。
            当然，这个参数也可以接收一个JSONGenerator，然后通过JSONGenerator来输出转换后的信息。第二个参数是将要被转换的Java对象。
            如果用三个参数的方法，那么是一个Config。这个config可以提供一些转换时的规则，过指定的Java对象的某些属性进行过滤或转换等。*/
            System.out.println("ObjectMapper");
            //writeValue具有和writeObject相同的功能
            objectMapper.writeValue(System.out, bean);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将map转换成json字符串
     */
    @Test
    public void writeMapJSON() {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("name", bean.getName());
            map.put("account", bean);
            bean = new AccountBean();
            bean.setAddress("china-Beijin");
            bean.setEmail("hoojo@qq.com");
            map.put("account2", bean);

            System.out.println("jsonGenerator");
            jsonGenerator.writeObject(map);
            System.out.println("");

            System.out.println("objectMapper");
            objectMapper.writeValue(System.out, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将list集合转换成json字符串,Array转换同list
     */
    @Test
    public void writeListJSON() {
        try {
            List<AccountBean> list = new ArrayList<>();
            list.add(bean);

            bean = new AccountBean();
            bean.setId(2);
            bean.setAddress("address2");
            bean.setEmail("email2");
            bean.setName("haha2");
            list.add(bean);

            System.out.println("jsonGenerator");
            //list转换成JSON字符串
            jsonGenerator.writeObject(list);

            System.out.println();

            System.out.println("ObjectMapper");
            //用objectMapper直接返回list转换成的JSON字符串
            System.out.println("1###" + objectMapper.writeValueAsString(list));
            System.out.print("2###");
            //objectMapper list转换成JSON字符串
            objectMapper.writeValue(System.out, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeOthersJSON() {
        try {
            String[] arr = {"a", "b", "c"};
            System.out.println("jsonGenerator");
            String str = "hello world jackson!";
            //byte
            jsonGenerator.writeBinary(str.getBytes());
            //boolean
            jsonGenerator.writeBoolean(true);
            //null
            jsonGenerator.writeNull();
            //float
            jsonGenerator.writeNumber(2.2f);
            //char
            jsonGenerator.writeRaw("c");
            //String
            jsonGenerator.writeRaw(str, 5, 10);
            //String
            jsonGenerator.writeRawValue(str, 5, 5);
            //String
            jsonGenerator.writeString(str);
            jsonGenerator.writeTree(JsonNodeFactory.instance.pojoNode(str));

            System.out.println();

            //自定义json
            jsonGenerator.writeStartObject();//{
            jsonGenerator.writeObjectFieldStart("user");//user:{
            jsonGenerator.writeStringField("name", "jackson");//name:jackson
            jsonGenerator.writeBooleanField("sex", true);//sex:true
            jsonGenerator.writeNumberField("age", 22);//age:22
            jsonGenerator.writeEndObject();//}

            jsonGenerator.writeArrayFieldStart("infos");//infos:[
            jsonGenerator.writeNumber(22);//22
            jsonGenerator.writeString("this is array");//this is array
            jsonGenerator.writeEndArray();//]

            jsonGenerator.writeEndObject();//}


            AccountBean bean = new AccountBean();
            bean.setAddress("address");
            bean.setEmail("email");
            bean.setId(1);
            bean.setName("haha");
            //complex Object
            jsonGenerator.writeStartObject();//{
            jsonGenerator.writeObjectField("user", bean);//user:{bean}
            jsonGenerator.writeObjectField("infos", arr);//infos:[array]
            jsonGenerator.writeEndObject();//}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    JSON转换成Java对象
     */
    @Test
    public void readJson2Entity() {
        String json = "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}";
        try {
            AccountBean acc = objectMapper.readValue(json, AccountBean.class);
            System.out.println(acc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * json字符串转换成list<map>
     */
    @Test
    public void readJson2List() {
        String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
        try {
            // List中存放AccountBean失败。支持Map集合。转成List.class，不知道List存放何种类型。默认LinkedHashMap。因为所有对象都可以转换成Map
            List<LinkedHashMap<String, Object>> list = objectMapper.readValue(json, List.class);

            // List<AccountBean> list = objectMapper.readValue(json, List.class);
            // ClassCastException: LinkedHashMap cannot be cast to AccountBean
            // AccountBean accountBean = list.get(0);

            System.out.println(list);
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                Set<String> set = map.keySet();
                for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
                    String key = it.next();
                    System.out.println(key + ":" + map.get(key));
                }
                System.out.println("-----------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * json字符串转换成Array
     * 上面泛型转换不能识别集合中的对象类型。
     * 用对象数组，可以解决这个问题。可以用Arrays.asList将其转换成List
     */
    @Test
    public void readJson2Array() {
        String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
        try {
            AccountBean[] arr = objectMapper.readValue(json, AccountBean[].class);
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * json字符串转换Map集合
     */
    @Test
    public void readJson2Map() {
        String json = "{\"success\":true,\"A\":{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},\"B\":{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}}";
        try {
            // 结构 key value(boolean),key value(LinkedHashMap),key value(LinkedHashMap)
            Map<String, Map<String, Object>> maps = objectMapper.readValue(json, Map.class);
            System.out.println(maps.size());

            Set<String> key = maps.keySet();
            Iterator<String> iter = key.iterator();
            while (iter.hasNext()) {
                String field = iter.next();
                /*
                success:true
                A:{address=address2, name=haha2, id=2, email=email2}
                B:{address=address, name=haha, id=1, email=email}
                */
                System.out.println(field + ":" + maps.get(field));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class AccountBean implements Serializable {
    private int id;
    private String name;
    private String email;
    private String address;
    private Birthday birthday;

    @Override
    public String toString() {
        return this.name + "#" + this.id + "#" + this.address + "#" + this.birthday + "#" + this.email;
    }

    public void setBirthday(Birthday birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Birthday getBirthday() {
        return birthday;
    }
}

class Birthday implements Serializable {
    private String birthday;

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public Birthday() {
    }

    public Birthday(String birthday) {
        super();
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return this.birthday;
    }
}
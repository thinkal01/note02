package com.note.common.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.IOException;

public class ObjectMapper01 {
    @Test
    public void test01() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        // Long转为String
        LongSerializer longSerializer = new LongSerializer();
        /*module.addSerializer(Long.class, longSerializer);
        module.addSerializer(Long.TYPE, longSerializer);*/
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(module);

        System.out.println(objectMapper.writeValueAsString(new Item()));
    }

    @Test
    public void test02() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(new Item()));
    }

    @Test
    public void test03() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Item.class, new ItemDeserializer());
        objectMapper.registerModule(module);

        Item readValue = objectMapper.readValue("{\"itemId\":1,\"itemName\":\"theItem\",\"userId\":2}", Item.class);
        System.out.println(readValue);
    }

    class LongSerializer extends JsonSerializer<Long> {
        @Override
        public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            if (value != null) {
                gen.writeString(value.toString());
            }
        }
    }
}

class ItemSerializer extends JsonSerializer<Item> {
    @Override
    public void serialize(Item value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        // long 转 string
        gen.writeStringField("itemId", value.itemId.toString());
        gen.writeStringField("itemName", value.itemName);
        gen.writeNumberField("owner", value.owner.userId);
        gen.writeEndObject();
    }
}

class ItemDeserializer extends JsonDeserializer<Item> {
    @Override
    public Item deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        int id = (int) node.get("itemId").numberValue();
        String itemName = node.get("itemName").asText();
        int userId = (Integer) node.get("userId").numberValue();
        return new Item((long) id, itemName, new ItemUser(userId, null));
    }
}

@Data
@AllArgsConstructor
class ItemUser {
    public int userId;
    public String name;
}

// 直接添加以下注解即可
// 此处不支持内部类Serializer,因为内部类构造函数的第一个参数是外部类.this,不能通过空参反射拿到空参构造
// @JsonSerialize(using = ItemSerializer.class)
// @JsonDeserialize(using = ItemDeserializer.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class Item {
    // 方法一,添加以下注解,long转string
    // @JsonSerialize(using = ToStringSerializer.class)
    public Long itemId = Long.valueOf(100);
    public String itemName = "itemName";
    public ItemUser owner = new ItemUser(101, "user01");
}
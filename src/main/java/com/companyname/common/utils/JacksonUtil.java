package com.companyname.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author HUANXI
 * @date 2021/8/31 19:02
 */
@Log4j2
public class JacksonUtil {

    private JacksonUtil() {
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //取消默认转换timesstamps(时间戳)形式
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //忽略空bean转json错误
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //忽略在json字符串中存在，在java类中不存在字段，防止错误
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //所有日期都统一为以下样式：yyyy-MM-dd HH:mm:ss
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        OBJECT_MAPPER.registerModule(javaTimeModule);
    }

    public static <T> String objToJson(T obj) {
        if (obj == null) {
            return null;
        }

        try {
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("obj To json is error", e);
            return null;
        }
    }


    /**
     * 返回格式化好的json串
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String objToJsonPretty(T obj) {
        if (obj == null) {
            return null;
        }

        try {
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("obj To json pretty is error", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T json2Object(String json, Class<T> clazz) {
        if (ObjectUtils.isEmpty(json) || clazz == null) {
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T) json : OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            log.warn("json To obj is error", e);
            return null;
        }
    }

    /**
     * 通过   TypeReference    处理List<User>这类多泛型问题
     *
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T,U> T json2Object(String json, TypeReference<U> typeReference) {
        if (ObjectUtils.isEmpty(json) || typeReference == null) {
            return null;
        }


        try {
            Object obj = OBJECT_MAPPER.readValue(json, typeReference);
            Object bean = typeReference.getType().equals(String.class) ? json : obj;
            return (T) bean;
        } catch (Exception e) {
            log.warn("json To obj is error", e);
            return null;
        }
    }

    /**
     * 通过jackson 的javatype 来处理多泛型的转换
     *
     * @param json
     * @param collectionClazz
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> T json2Object(String json, Class<?> collectionClazz, Class<?>... elements) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClazz, elements);

        try {
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            log.warn("json To obj is error", e);
            return null;
        }
    }
}

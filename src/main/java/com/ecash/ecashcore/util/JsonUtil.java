package com.ecash.ecashcore.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {

  public static String objectToJsonString(Object obj) {
    ObjectWriter ow = new ObjectMapper().writer();
    ow = ow.without(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    String json = null;
    try {
      json = ow.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("serialize json error", e);
    }

    return json;
  }

  public static <T> T jsonStringToObject(String json, Class<T> clazz) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    T object = null;
    try {
      object = mapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new RuntimeException("deserialize json error", e);
    }

    return object;
  }
}

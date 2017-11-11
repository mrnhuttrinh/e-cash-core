package com.ecash.ecashcore.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtil {

  public static String objectToJsonString(Object obj) {
    ObjectWriter ow = new ObjectMapper().writer();
    String json = null;
    try {
      json = ow.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("serialize json error", e);
    }

    return json;
  }

  public static Object jsonStringToObject(String json, Class<?> clazz) {
    ObjectMapper mapper = new ObjectMapper();
    Object object = null;
    try {
      object = mapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new RuntimeException("deserialize json error", e);
    }

    return object;
  }
}
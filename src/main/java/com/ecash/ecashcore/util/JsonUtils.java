package com.ecash.ecashcore.util;

import java.io.IOException;

import com.ecash.ecashcore.exception.EcashException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

public class JsonUtils {

  public static String objectToJsonString(Object obj) {
    ObjectMapper mapper = new ObjectMapper();

    ObjectWriter ow = mapper.writer();
    ow = ow.without(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    String json = null;
    try {
      json = ow.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new EcashException("serialize json error", e);
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
      throw new EcashException("deserialize json error", e);
    }

    return object;
  }

  public static String objectToJsonStringGson(Object obj) {
    Gson gson = new Gson();
    String json = null;
    try {
      json = gson.toJson(obj);
    } catch (Exception e) {
      throw new EcashException("serialize json error", e);
    }

    return json;
  }

  public static <T> T jsonStringToObjectGson(String json, Class<T> clazz) {
    Gson gson = new Gson();
    T object = null;
    try {
      object = gson.fromJson(json, clazz);
    } catch (Exception e) {
      throw new EcashException("deserialize json error", e);
    }

    return object;
  }
}

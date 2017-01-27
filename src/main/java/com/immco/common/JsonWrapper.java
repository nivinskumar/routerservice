package com.immco.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * The wrapper class for JSON implementation. Exposed as a Service, it can be autowired
 * 
 * @author shajeelawrence
 *
 */
public class JsonWrapper {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Gson gson = new GsonBuilder().create();

    public JsonWrapper()
    {
	//Fully qualified path shows I am using latest enum
	objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS , false);

    }
    public String toJSON(Object value) throws JsonProcessingException {
	return objectMapper.writeValueAsString(value);
    }

    public <T> T fromJSON(String content, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
	return objectMapper.readValue(content, valueType);
    }

    public <T> List<T> fromGSONAsList(String json, Class<T> t) throws IOException, JsonParseException, JsonMappingException {
	JsonArray jsonArray = gson.fromJson(json, JsonArray.class);
	List<T> list = new ArrayList<>();
	for (JsonElement jsonElement : jsonArray) {
	    T pojo = gson.fromJson(jsonElement, t);
	    list.add(pojo);
	}
	return list;
    }

    public <T> T fromGSON(String content, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
	return gson.fromJson(content, valueType);
    }

    public <T> T fromJSON(String content, TypeReference<T> t) throws IOException, JsonParseException, JsonMappingException {
	return objectMapper.readValue(content, t);
    }

    public <T> T parseIPersistable(String json, Class<T> t) throws Exception {
	JsonObject job = gson.fromJson(json, JsonObject.class);
	JsonElement jsonElement = job.get("ipersistableMap");
	String pojoElement = jsonElement.getAsJsonObject().get(t.getName()).toString();
	T pojo = fromJSON(pojoElement, t);
	return pojo;
    }
}
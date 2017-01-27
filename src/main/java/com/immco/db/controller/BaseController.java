package com.immco.db.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.immco.common.JsonWrapper;

public class BaseController {
    @Autowired
    private JsonWrapper jsonWrapper;

    public String defaultErrMsg() {
	return "<ERROR> An Error occured.  Please check the Db Service log for more information";
    }

    public String getErrormsg(Exception e) {
	return "<ERROR> An Error occured. " + e.getMessage();
    }

    public String toJson(Object value) throws JsonProcessingException {
	return jsonWrapper.toJSON(value);
    }

    public <T> T fromJSON(String content, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
	return jsonWrapper.fromJSON(content, valueType);
    }

    public <T> T parseIPersistable(String json, Class<T> t) throws Exception {
	return jsonWrapper.parseIPersistable(json, t);
    }
    
    public <T> List<T> getAllObjects(String json, Class<T> t) throws Exception {
	return jsonWrapper.fromGSONAsList(json, t);
    }
}

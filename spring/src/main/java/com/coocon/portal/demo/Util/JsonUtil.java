package com.coocon.portal.demo.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String makeJsonStringByObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

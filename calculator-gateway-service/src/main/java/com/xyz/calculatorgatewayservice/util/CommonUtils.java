package com.xyz.calculatorgatewayservice.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xyz.calculatorgatewayservice.constants.ErrorCodes;
import com.xyz.calculatorgatewayservice.exception.GenericException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtils {

	private static Gson gson = new GsonBuilder().create();
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String toJson(Object o) {
		return gson.toJson(o);
	}

	public static <T> T fromJson(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}

	public static <T> T mapObject(Object object, Class<T> type) {
		return objectMapper.convertValue(object, type);
	}

	public static <T> T readValue(String json, TypeReference<T> valueTypeRef) throws GenericException {
		T t = null;
		try {
			t = objectMapper.readValue(json, valueTypeRef);
		} catch (Exception e) {
			log.error("Exception while mapping object for json {} to {} with ", json, valueTypeRef, e);
			throw new GenericException(ErrorCodes.CA004, ErrorCodes.getErrorMsg(ErrorCodes.CA004));
		}
		return t;
	}

}

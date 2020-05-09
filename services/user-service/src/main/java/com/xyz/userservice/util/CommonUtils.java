package com.xyz.userservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommonUtils {

	private static Gson gson = new GsonBuilder().create();
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String toJson(Object o) {
		return gson.toJson(o);
	}

	public static <T> T mapObject(Object object, Class<T> type) {
		return objectMapper.convertValue(object, type);
	}
}

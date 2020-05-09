package com.xyz.notificationservice.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xyz.notificationservice.constants.AppConstants;

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

	public static String getStringDate(Long timeStamp) {
		SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.CALENDAR_DATE_FORMAT);
		String date = sdf.format(timeStamp);
		return date.toString();
	}
}

package br.com.softplan.marcusvoltolim.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import java.util.Date;

public class JsonUtils {
	
	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> {
				try {
					return new Date(json.getAsJsonPrimitive().getAsLong());
				} catch (Exception e) {
					return null;
				}
			})
			.registerTypeAdapter(Date.class, (JsonSerializer<Date>) (date, type, jsonSerializationContext) ->
			{
				try {
					return new JsonPrimitive(date.getTime());
				} catch (Exception e) {
					return JsonNull.INSTANCE;
				}
			})
			.create();
	
	private JsonUtils() {
	}
	
	
	public static <T> T fromJson(String json, Class<T> classOfT) {
		return GSON.fromJson(json, classOfT);
	}
	
}

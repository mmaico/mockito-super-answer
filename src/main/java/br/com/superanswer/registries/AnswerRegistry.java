package br.com.superanswer.registries;

import java.util.HashMap;
import java.util.Map;

public class AnswerRegistry {

	private static Map<Class<? extends Object>, Map<String, Object>> methodMockMap = new HashMap<Class<? extends Object>, Map<String, Object>>();
	
	
	public static void registry(Class<? extends Object> clazz, String methodName, Object mock) {
		
		methodMockMap.put(clazz, addMethod(clazz, methodName, mock));
	}
	
	private static Map<String, Object> addMethod(Class<? extends Object> clazz, 
			String methodName, Object mock) {
		
		if (methodMockMap.get(clazz) == null) {
			methodMockMap.put(clazz, new HashMap<String, Object>());
		}
		
		Map<String, Object> methodsMapped = methodMockMap.get(clazz);
		methodsMapped.put(methodName, mock);
		
		return methodsMapped;
	}

	public static Map<Class<? extends Object>, Map<String, Object>> getMocksMap() {
		return methodMockMap;
	}
}

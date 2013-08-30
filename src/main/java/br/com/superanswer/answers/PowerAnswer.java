package br.com.superanswer.answers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.mockito.Mockito;
import org.mockito.internal.creation.jmock.ClassImposterizer;
import org.mockito.internal.stubbing.defaultanswers.ReturnsMoreEmptyValues;
import org.mockito.internal.util.MockUtil;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.mock.MockCreationSettings;
import org.mockito.stubbing.Answer;

import br.com.superanswer.registries.AnswerRegistry;


public class PowerAnswer implements Answer<Object>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<Class<? extends Object>, ObjectByMethod> methodMockMap = new HashMap<Class<? extends Object>, ObjectByMethod>();
	
	private Answer<Object> delegate = new ReturnsMoreEmptyValues();
	
	@SuppressWarnings("unchecked")
	public Object answer(InvocationOnMock invocation) throws Throwable {
		
		Object answer = delegate.answer(invocation);
		
		if (answer != null) {
			return answer;
		}
		Object returnValueFor = returnValueFor(invocation.getMethod().getReturnType());
		
		Class<? extends Object> objectClazz = getTypeToMock(invocation.getMock());
		
		String methodName = invocation.getMethod().getName();
		
		AnswerRegistry.registry(objectClazz, methodName, invocation.getMock());
		
		
		return returnValueFor;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getMock(Class<T> clazz, String methodName) {
		ObjectByMethod objectByMethod = methodMockMap.get(clazz);
		
		if (objectByMethod.methodName.equals(methodName)) {
			return  (T) objectByMethod.object;
		}
		
		return null;
	}
	
	public static PowerAnswer build() {
		return new PowerAnswer();
	}
	
	Object returnValueFor(Class<?> clazz) {
		if (!ClassImposterizer.INSTANCE.canImposterise(clazz)) {
			return null;
		}
		
		return Mockito.mock(clazz, PowerAnswer.build());
	}
	
	public class ObjectByMethod {
		private String methodName;
		private Object object;
		
		public ObjectByMethod(String methodName, Object object) {
			this.methodName = methodName;
			this.object = object;
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public Class getTypeToMock(Object a) {
		MockCreationSettings mockSettings = new MockUtil().getMockHandler(a).getMockSettings();
		
		return mockSettings.getTypeToMock();
	}
		
}

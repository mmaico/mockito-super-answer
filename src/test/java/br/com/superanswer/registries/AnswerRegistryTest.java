package br.com.superanswer.registries;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;

import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;


public class AnswerRegistryTest {

	
	@Test
	public void shouldRegistryMockAssociatedWithTheMock() {
		UserTest mock = Mockito.mock(UserTest.class);
		
		AnswerRegistry.registry(UserTest.class, "get", mock);
		
		Map<Class<? extends Object>, Map<String, Object>> mocksMap = AnswerRegistry.getMocksMap();
	
		Map<String, Object> methodsMapped = mocksMap.get(UserTest.class);
		Object objectMockReturned = methodsMapped.get("get");
		
		assertThat((UserTest)objectMockReturned, sameInstance(mock));
	}
	
	@Test
	public void shouldRegistryManyMethodsForAClass() {
		UserTest mock = Mockito.mock(UserTest.class);
		AddressTest addressMock = Mockito.mock(AddressTest.class);
		
		AnswerRegistry.registry(UserTest.class, "get", mock);
		AnswerRegistry.registry(UserTest.class, "otherMethod", addressMock);
		
		Map<Class<? extends Object>, Map<String, Object>> mocksMap = AnswerRegistry.getMocksMap();
	
		Map<String, Object> methodsMapped = mocksMap.get(UserTest.class);
		UserTest objectMockReturned = (UserTest) methodsMapped.get("get");
		AddressTest objectMockReturnedOtherMethod = (AddressTest) methodsMapped.get("otherMethod");
		
		assertThat((UserTest)objectMockReturned, sameInstance(mock));
		assertThat(objectMockReturnedOtherMethod, sameInstance(addressMock));
		
	}

	
	public static class UserTest {
		
		public UserTest get() {
			
			return null;
		}
		
		public AddressTest otherMethod() {
			return new AddressTest();
		}
	}
	
	public static class AddressTest {
		
	}
}

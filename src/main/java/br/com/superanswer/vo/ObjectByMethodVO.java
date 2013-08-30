package br.com.superanswer.vo;

public class ObjectByMethodVO {

	private String methodName;
	private Object object;
	
	public ObjectByMethodVO(String methodName, Object object) {
		this.methodName = methodName;
		this.object = object;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public static ObjectByMethodVO build(String methodName, Object object) {
		return new ObjectByMethodVO(methodName, object);
	}
}

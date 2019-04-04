package jai.handson.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
	
	public static void main(String[] arg) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		// Starting of the application.
		StringInstrument stringInstrument = createInstance(new Foo());
		stringInstrument.play();
		
		Foo f = new Foo();
		Class class1 = f.getClass();
		Foo f2 = new Foo();
		Class class2 = Foo.class;
		Class class3 = Class.forName("jai.handson.proxy.Foo");
		
		if(class1 == class3) {
			System.out.println("WOW They are equal.");
		}
	}
	
	public static <T> T createInstance(T instance){
		return (T)Proxy.newProxyInstance(instance.getClass().getClassLoader(), 
				instance.getClass().getInterfaces(), new MyInvocationHandler(instance));
	}
	
	public static final class MyInvocationHandler<T> implements InvocationHandler{

		private final T actualInstance;
		
		public MyInvocationHandler(T actualInstance) {
			this.actualInstance = actualInstance;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
			return method.invoke(actualInstance, args);
		}
		
	}
}

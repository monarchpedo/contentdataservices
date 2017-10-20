package com.storyshell.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import com.storyshell.contentdataservices.GenericExceptionHandler;
import com.storyshell.dao.ContentData;

/**
 * 
 * @author Monarchpedo
 *
 *class<V> genericClass can be used as a parameter type to call declaremethod of particular return type method
 * @param <T>
 */
public class ExecutorServiceCallable<T> implements Callable<T> {

	private int userId;
	private String methodName;
	private T a;

	public ExecutorServiceCallable(int userId, String methodName, T a) {
		this.methodName = methodName;
		this.userId = userId;
		this.a = a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T call() {
		Method method = null;
		T result = null;
		try {
			method = this.a.getClass().getDeclaredMethod(methodName, int.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new GenericExceptionHandler(e.getMessage());
		} catch (SecurityException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		try {
			result = (T) method.invoke(this.a, userId);
		} catch (IllegalAccessException e) {
			throw new GenericExceptionHandler(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new GenericExceptionHandler(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return result;
	}

}

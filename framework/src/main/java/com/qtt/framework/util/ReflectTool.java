/**
 * 
 */
package com.qtt.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lei
 *
 */
public enum ReflectTool {
	;
	
	public static Method getDeclaredMethod(Class<?> clazz ,String name, Class<?>... parameterTypes){
		try {
			return clazz.getDeclaredMethod(name, parameterTypes);
		} catch (NoSuchMethodException e) {
			if(clazz.getSuperclass()!=null)
				return getDeclaredMethod(clazz.getSuperclass(), name, parameterTypes);
			else
				return null;
		}

	}
	
	public static Method getMethod(Object obj,String name){
		return getMethod(obj.getClass(), name);
	}
	
	
	public static Method getMethod(Class<?> clazz,String name){
		Set<Method> set = new HashSet<Method>();
		set.addAll(Arrays.asList(clazz.getDeclaredMethods()));
		set.addAll(Arrays.asList(clazz.getMethods()));
		//Method[] methods = set.toArray(new Method[0]);
		for(Method m : set){
			if(m.getName().equals(name)){
				return m;
			}
		}
		
		return null;
		
	}
	
	public static boolean containsInterface(Class<?> resource ,Class<?> interfaceClass){
		for(Class<?> c :resource.getInterfaces()){
			if(c.equals(interfaceClass))
				return true;
		}
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
/*	public static <T> T  getPropertiy(Object target, String name, Class<T> returnClass){
		if(target==null)
			throw new RuntimeException("The target object is null!");
		
		if(name==null || name.length()<=0)
			throw new RuntimeException("The name is null or empty!");
		
		if(returnClass==null)
			throw new RuntimeException("The returnClass is null!");
		
	        T t = null;    
		
			try {

				Object o = target.getClass().getField(name).get(target);
				if(returnClass.isAssignableFrom(o.getClass()))
					t = (T) o;

			} catch (NoSuchFieldException e) {
				throw new RuntimeException("The " +  name  + " is not exist in " + target.getClass().getName());
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}

		return t;
	}*/
	

	public static <T> T  getPropertiy(Object target, String name, Class<T> returnClass){
		if(target==null)
			throw new RuntimeException("The target object is null!");
		
		if(name==null || name.length()<=0)
			throw new RuntimeException("The name is null or empty!");
 
	        Object o = null;
			try {
				o = target.getClass().getField(name).get(target);
			} catch (NoSuchFieldException e) {
				throw new RuntimeException("The " +  name  + " is not exist in " + target.getClass().getName());
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
			try {
				o = target.getClass().getDeclaredField(name).get(target);
			} catch (NoSuchFieldException e) {
				if(target.getClass().getSuperclass()==null)
					o = null;
				else
					o = getPropertiy(target.getClass().getSuperclass(),name,returnClass);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			
			if(returnClass.isAssignableFrom(o.getClass()))
				return (T) o;
			else
				return null;

	}
	
	
	
	public static Field  getField(Class<?> target, String name){
		if(target==null)
			throw new RuntimeException("The target object is null!");
		
		if(name==null || name.length()<=0)
			throw new RuntimeException("The name is null or empty!");
 
		
		Field[] fields = target.getDeclaredFields();
		for(Field field : fields){
			//Log.e("zzzzz---->", field.getName());
			if(field.getName().equals(name))
				return field;
		}
		
		if(target.getSuperclass()!=null)
			return getField(target.getSuperclass(),name);
		else
			return null;
	}
	
}

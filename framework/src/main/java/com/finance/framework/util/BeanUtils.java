package com.finance.framework.util;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lilei
 * 
 */
public final class BeanUtils {

	public final static class BeanUtilsException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public BeanUtilsException(String detailMessage) {
			super(detailMessage);
		}
	}

	private BeanUtils() {
	}

	private static boolean contains(String target, String... sources) {
		for (String temp : sources) {
			if (target.equals(temp))
				return true;
		}
		return false;
	}

	public static Object newInstance(Object value) {
		try {
			return value.getClass().newInstance();
		} catch (Exception e) {
			throw new BeanUtilsException("The " + value.getClass() + " doesn't have a default constructor!");
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Object process1(Object value, int depth,String... ignoreProperties){
		if (value == null || depth==0) 
			return value;
		

		if (value instanceof Map) {

			Object t = newInstance(value);
			copyProperties(value, t, depth,ignoreProperties);
			return t;

		} else if (value.getClass().isArray() || value instanceof Collection) {
			// 数组或集合

			Object[] result = new Object[1];
			if (value.getClass().isArray()) {
				result = (Object[]) value;
			} else {
				result = ((Collection) value).toArray();
			}

			List<Object> m = new ArrayList<Object>();

			for (Object s : result) {
				Object t = newInstance(value);
				copyProperties(s, t,depth, ignoreProperties);
				m.add(t);
			}

			if (value.getClass().isArray())
				return m.toArray();
			else {
				Collection t = (Collection) newInstance(value);
				t.addAll(m);
				return t;
			}

		} else
			// 是JAVA基础类型
			return value;
		
	}
	
	/**
	 * 可以指定复制深度，超类非public 的 属性是不会被复制的
	 * @param source 可以是复合对象，也可以是Map
	 * @param target 可以是复合对象，也可以是Map
	 * @param depth -1根复制，一直复制到根结点,0仅复制一层
	 * @param ignoreProperties 不要复制的属性
	 */
	@SuppressWarnings({ "unchecked" })
	public static void copyProperties(Object source, Object target, int depth,String... ignoreProperties){
		verifyUsableForCopyProperties(source);
		verifyUsableForCopyProperties(target);

		boolean sourceIsMap = source instanceof Map;
		boolean targetIsMap = target instanceof Map;

		if (targetIsMap) {

			Map<Object, Object> targetMap = (Map<Object, Object>) target;

			if (sourceIsMap) {
				Set<Map.Entry<String, Object>> sourceSet = Map.class.cast(source).entrySet();

				for (Map.Entry<String, Object> entry : sourceSet) {

					if (!contains(entry.getKey(), ignoreProperties)) {
						Object obj = process1(entry.getValue(),(depth>0?depth--:depth),ignoreProperties);
						if(obj!=null)
							targetMap.put(entry.getKey(), process1(entry.getValue(),(depth>0?depth--:depth),ignoreProperties));
					}

				}

			} else {
				
				Set<Field> set = new HashSet<Field>();
				set.addAll(Arrays.asList(source.getClass().getDeclaredFields()));
				set.addAll(Arrays.asList(source.getClass().getFields()));
				Field[] sourceFields = set.toArray(new Field[0]);
				
				for(Field filed : sourceFields){
					filed.setAccessible(true);
					if (!contains(filed.getName(), ignoreProperties)) {
						
						try {
							Object obj = process1(filed.get(source),(depth>0?depth--:depth),ignoreProperties);
							if(obj!=null)
								targetMap.put(filed.getName(), obj);
						} catch (IllegalAccessException e) {
							throw new BeanUtilsException(e.getMessage());
						}
						
					}
					
				}
				
			}
			
		} else {

			
			Set<Field> set = new HashSet<Field>();
			set.addAll(Arrays.asList(target.getClass().getDeclaredFields()));
			set.addAll(Arrays.asList(target.getClass().getFields()));
			Field[] targetFields = set.toArray(new Field[0]);

			for (Field f : targetFields) {
				f.setAccessible(true);
				if (!contains(f.getName(), ignoreProperties)) {

					try{
						Object o;
						if (sourceIsMap) {
							Map<String, Object> sourceMap =  Map.class.cast(source);
							o = sourceMap.get(f.getName());
						}else{
							o = getField(source,f.getName());
						}
					
						Object obj = process1(o,(depth>0?depth--:depth),ignoreProperties);
						if(obj!=null)
							f.set(target, obj);
					}catch(Exception e){
						throw new BeanUtilsException(e.getMessage());
					}

				}

			}

		}
	}

	/**
	 * 非深度复制，超类非public 的 属性是不会被复制的
	 * @param source 可以是复合对象，也可以是Map
	 * @param target 可以是复合对象，也可以是Map
	 * @param ignoreProperties 不要复制的属性
	 */
	
	public static void copyProperties(Object source, Object target, String... ignoreProperties) {
		copyProperties(source, target, 0, ignoreProperties);

	}
	
	public static Object getField(Object source,String name){
		try{
			try{
				Field m1 = source.getClass().getDeclaredField(name);
				m1.setAccessible(true);
				return m1.get(source);
			}catch(NoSuchFieldException e){
				try {
					Field m1 = source.getClass().getField(name);
					m1.setAccessible(true);
					return m1.get(source);
				} catch (NoSuchFieldException e1) {
					System.out.println("Can't find the '" + name + "' property in " + source.getClass().getName());
					return null;
				}
				
			}
		}catch(IllegalAccessException iae){
			throw new BeanUtilsException(iae.getMessage());
		}

	}

	public static void verifyUsableForCopyProperties(Object obj) {
		if (obj == null)
			throw new BeanUtilsException("The parameters can't be null!");

		if (obj instanceof Number || obj instanceof String || obj instanceof Character || obj instanceof Collection
				|| obj.getClass().isArray())

			throw new BeanUtilsException("The " + obj + " is " + obj.getClass().getSimpleName() + " can't used for copyProperties!");

	}

	public static boolean isBaseType(Object obj) {
		if (obj == null)
			throw new BeanUtilsException("The parameters can't be null!");

		if (obj instanceof Number || obj instanceof String || obj instanceof Character)
			return true;
		else
			return false;
	}
	

	
	//---------------------------------- test 仅用于在标准JAVA运行测试 -------------------------------------------------------------------------
	public static class User {
		private String name;
		public Address address;
		public Map<String,?> session;
		
		@Override
		public String toString() {
			return "{name:"+name+",address:"+address+",session:"+session+"}";
		}
	}
	
	
	
	
	
	public static class Address {
		private String home="千阳南路99弄";
		protected String company;
		
		@Override
		public String toString() {
			return "{home:" + home + ",company:"+company+"}";
		}
	}	
	
	
	public class Session {
		public int sid;
		public Date createTime;
		public Calendar updateTime;
	}

	
	
	
	
	
	public static void main(String[] args) {
		Address addr = new Address();
		addr.company = "福泉路99号";
		
		Map<String,Object> session = new HashMap<String,Object>();
		session.put("sid", 1111);
		session.put("createTime", Calendar.getInstance());
		
		User u = new User();
		u.address = addr;
		u.session = session;
		
		Map<Object,Object> target = new HashMap<Object,Object>();
		BeanUtils.copyProperties(u, target, "");
		System.out.println(target);
		
		User u2 = new User();
		BeanUtils.copyProperties(target, u2, "");
		System.out.println(u2);
		
		User u3 = new User();
		BeanUtils.copyProperties(u2, u3, "");
		
		System.out.println(u3);
		
	}
	//---------------------------------- test 仅用于在标准JAVA运行测试 -------------------------------------------------------------------------
}
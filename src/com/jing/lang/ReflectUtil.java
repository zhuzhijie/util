package com.jing.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.jing.util.CollectionUtil;


/**
 * 反射综合类。
 * @author 朱志杰 QQ：862990787
 * Sep 21, 2013 10:06:08 PM
 */
public class ReflectUtil {
	
	/**
	 * 功能：执行对象中的某个函数。
	 * @author 朱志杰 QQ：862990787
	 * 2013-10-9 上午11:44:12
	 * @param instance 对象
	 * @param methodName 函数名
	 * @param args 参数
	 * @return Object 执行后函数的返回值
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static Object invokeMethod(Object instance,String methodName,Object... args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException{
		//根据参数得到对应的类型数组
		Class[] argArr=null;
		if(!CollectionUtil.isEmpty(args)){
			argArr=new Class[args.length];
			for(int i=0 ; i<args.length ; i++){
				argArr[i]=args[i].getClass();
			}
		}
		Method m = instance.getClass().getDeclaredMethod(methodName,argArr);
        //执行该类的中该方法
        return m.invoke(instance, args);
	}
	
	/**
	 * 功能：得到某个类的一个实例。
	 * @author 朱志杰 QQ：862990787
	 * Sep 21, 2013 10:01:16 PM
	 * @param classType 全类名(含包名)
	 * @return <T> T
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static <T> T getInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class testClass = Class.forName(className);  
        // 调用缺省构造函数，直接用testClass调用就可  
        Object obj = testClass.newInstance();  
        return (T)obj;
	}
	
	/**
	 * 功能：得到某个类的所有方法。
	 * @author 朱志杰 QQ：862990787
	 * Sep 21, 2013 10:01:16 PM
	 * @param classType 类
	 * @return Method[]
	 */
	public static Method[] getAllMethod(Class<?> classType){
		Method[] methods = classType.getDeclaredMethods();
		return methods;
	}
	
	/**
	 * 功能：执行一个静态函数。
	 * @author 朱志杰 QQ：862990787
	 * Sep 16, 2013 11:44:55 AM
	 * @param cls 类
	 * @param methodName 静态函数名称
	 * @param args 参数
	 * @return Object 执行后的返回值
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static Object invokeStaticMethod(Class<?> cls, String methodName,
            Object... args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		int arguments = args.length;
        Class<?>[] parameterTypes = new Class[arguments];
        for (int i = 0; i < arguments; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        Method method = cls.getMethod(methodName, parameterTypes);
        return method.invoke(null, args);
	}
}

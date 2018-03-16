package com.lvdao.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.mongodb.DBObject;

/**
 * Bean工具类
 * 
 * @author zhxihu2008
 * @since 2016-07-18 16:57
 */
public class BeanUtils {

	/**
	 * DBObject Convert to bean
	 * 
	 * @author zhxihu2008
	 * @since 2016-07-18 16:58
	 * @param dbObject
	 * @param bean
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static <T> T dbObjectToBean(DBObject dbObject, T bean) throws IllegalAccessException, 
																		InvocationTargetException, 
																		NoSuchMethodException {
		if (bean == null) {
			return null;
		}
		
		Field[] fields = bean.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			String varName = field.getName();
			Object object = dbObject.get(varName);
			
			if (object != null) {
				BeanUtils.setProperty(bean, varName, object);
			}
		}
		
		return bean;
	}
	
	/**
	 * Set Property From MongoDB's DBObject
	 * 
	 * @author zhxihu2008
	 * @since 2016-07-18 16:58
	 * @param bean
	 * @param varName
	 * @param object
	 */
	public static <T> void setProperty(T bean, String varName, T object) {
		
        varName = varName.substring(0, 1).toUpperCase() + varName.substring(1);
        
        try {
            String type = object.getClass().getName();
            
            //类型为String
            if(type.equals("java.lang.String")) {
                Method m = bean.getClass().getMethod("get" + varName);
                String value = (String) m.invoke(bean);
                if (value == null) {
                    m = bean.getClass().getMethod("set" + varName, String.class);
                    m.invoke(bean, object);
                }
            }
            
            //类型为Integer
            if(type.equals("java.lang.Integer")) {
                Method m = bean.getClass().getMethod("get" + varName);
                String value = (String) m.invoke(bean);
                if (value == null) {
                    m = bean.getClass().getMethod("set" + varName,
                            Integer.class);
                    m.invoke(bean, object);
                }
            }
            
            //类型为Boolean
            if(type.equals("java.lang.Boolean")) {
                Method m = bean.getClass().getMethod("get" + varName);
                String value = (String) m.invoke(bean);
                if (value == null) {
                    m = bean.getClass().getMethod("set" + varName,
                            Boolean.class);
                    m.invoke(bean, object);
                }
            }
            
            //类型为Util Date
            if(type.equals("java.util.Date")) {
                Method m = bean.getClass().getMethod("get" + varName);
                Date value = (Date) m.invoke(bean);
                if (value == null) {
                    m = bean.getClass().getMethod("set" + varName,
                            Boolean.class);
                    m.invoke(bean, object);
                }
            }
            
            //类型为SQL Date
            if(type.equals("java.util.Long")) {
                Method m = bean.getClass().getMethod("get" + varName);
                Long value = (Long) m.invoke(bean);
                if (value == null) {
                    m = bean.getClass().getMethod("set" + varName,
                            Boolean.class);
                    m.invoke(bean, object);
                }
            }

            //类型为Long
            if(type.equals("java.sql.Date")) {
                Method m = bean.getClass().getMethod("get" + varName);
                Date value = (Date) m.invoke(bean);
                if (value == null) {
                    m = bean.getClass().getMethod("set" + varName,
                            Boolean.class);
                    m.invoke(bean, object);
                }
            }
            
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
	
}

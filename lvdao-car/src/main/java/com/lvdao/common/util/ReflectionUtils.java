package com.lvdao.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lvdao.common.CommonConst;

public class ReflectionUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(ReflectionUtils.class);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> Class<T> getSuperClassGenericType(final Class clazz) {
        return getSuperClassGenricType(clazz, CommonConst.DIGIT_ZERO);
    }

    @SuppressWarnings({ "rawtypes" })
    public static Class getSuperClassGenricType(final Class clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();

        if(!(genType instanceof ParameterizedType)){
        	LOGGER.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if(index >= params.length || index < CommonConst.DIGIT_ZERO){
        	LOGGER.warn("Index: " + index + ", Size of " + clazz.getSimpleName()
                    + "'s Parameterized Type: " + params.length);
            return Object.class;
        }
        
        if(!(params[index] instanceof Class)){
        	LOGGER.info(clazz.getSimpleName() + "not set the actual class on superclass generic parameter.");
            return Object.class;
        }

        return (Class) params[index];
    }

}

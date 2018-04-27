package com.moke.mokeWork.service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis工具Service
 * @author Segoul
 *
 * @param <T>
 */

public interface RedisService<T> {

	//String格式开始
	
	void set(String key, String value);

    void set(String key, String value, long expiredTime);

    void set(String key, String value, long expiredTime, TimeUnit timeUnit);

    String get(String key);

    List<T> getList(String key, Class clazz);

    T getObject(String key);

    void delete(String key);

    void delete(Collection<String> keys);
    
    //String格式结束
}

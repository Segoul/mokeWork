package com.moke.mokeWork.common.rest;

import com.moke.mokeWork.common.constant.StatusCode;

/**
 * 返回一般数据格式
 * @author Segoul
 * @param <T>
 */

public class ResultData<T> extends AbstractResult {

	private T data;
	
	@SuppressWarnings(value = "unchecked")
    public static <T> ResultData<T> ok() {
        return new ResultData(StatusCode.SUCCESS);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResultData<T> error() {
        return new ResultData(StatusCode.FAIL);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResultData<T> error(StatusCode statusCode) {
        return new ResultData(statusCode);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResultData<T> error(String code, String message) {
        return new ResultData(code, message);
    }

    public ResultData(StatusCode statusCode) {
        super(statusCode);
    }

    public ResultData(String code, String message) {
        super(code, message);
    }

    public static <T> ResultData<T> one(T obj) {
        ResultData<T> res = new ResultData(StatusCode.SUCCESS);
        res.data = obj;
        return res;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

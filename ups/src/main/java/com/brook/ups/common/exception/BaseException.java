package com.brook.ups.common.exception;

/**
 * 自定义基础异常类
 */
public abstract class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaseException() {
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
}

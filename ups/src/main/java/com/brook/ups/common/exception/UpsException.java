package com.brook.ups.common.exception;

import com.brook.ups.common.enums.ExceptionCode;
import com.brook.ups.common.enums.ExceptionLevel;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ups自定义异常
 */
public class UpsException extends BaseException  {

	private static final long serialVersionUID = 1L;

	private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\r\n"); 
	
	/** 异常代码 */
	protected ExceptionCode code;
	/** 异常级别，默认为ERROR */
	protected ExceptionLevel level;

	/**
	 * 通过异常代码、异常原因信息、异常对象、异常级别抛出Ups异常
	 * @param code      异常代码
	 * @param message	异常的原因
	 * @param t			异常对象
	 * @param level	    异常级别
	 */
	public UpsException(ExceptionCode code, String message, Throwable t, ExceptionLevel level) {
	    super(message==null?"未知原因":message, t);
		// 设置代码
		this.code = code==null?ExceptionCode.C99999:code;
		// 设置级别
		this.level = level==null?ExceptionLevel.ERROR:level;
	}

    /**
     * 通过异常代码、异常原因信息、异常对象抛出Ups异常
     * @param code      异常代码
     * @param message	异常的原因
     * @param t			异常对象
     */
    public UpsException(ExceptionCode code, String message, Throwable t) {
        this(code, message, t, null);
    }

    /**
     * 通过异常代码、异常原因信息、异常级别抛出Ups异常
     * @param code      异常代码
     * @param message	异常的原因
     * @param level	    异常级别
     */
    public UpsException(ExceptionCode code, String message, ExceptionLevel level) {
        super(message==null?"未知原因":message);
        // 设置代码
        this.code = code==null?ExceptionCode.C99999:code;
        // 设置级别
        this.level = level==null?ExceptionLevel.ERROR:level;
    }

    /**
     * 通过异常代码、异常原因信息抛出Ups异常
     * @param code      异常代码
     * @param message	异常的原因
     */
    public UpsException(ExceptionCode code, String message) {
        this(code, message, (ExceptionLevel) null);
    }

	/**
	 *  空参构造函数
	 */
	public UpsException() {
		this(null,null,null,null);
	}

	/**
	 * 生成异常信息
	 */
	@Override
	public String toString() {
		StringBuilder errLog = new StringBuilder();
		// 显示异常信息
        errLog.append(getExceptionInfo());
        //包含cause，显示历史堆栈
        //errLog.append(getCauseInfo());
        return errLog.toString();
	}

    /**
     * 获得Ups异常信息
     */
	public String getExceptionInfo(){
        // 异常信息
        StringBuilder errLog = new StringBuilder();
        // 异常代码处理
        if (this.code == null) {
            code = ExceptionCode.C99999;
        }
        // 显示异常类型
        if(code.getType()!=null){
            errLog.append(LINE_SEPARATOR+"【异常类型：").append(code.getType().getValue()).append("】");
        }
        // 显示异常代码
        errLog.append("【异常代码：").append(code.name()).append("】");
        // 显示异常级别
        if (this.level == ExceptionLevel.WARN) {
            errLog.append("【异常级别：WARN】");
        } else {
            errLog.append("【异常级别：ERROR】" );
        }
        // 显示异常描述
        errLog.append("【异常描述：").append(code.getDescription()).append("】");
        // 显示起始异常
        errLog.append(LINE_SEPARATOR+"【异常原因：").append(super.getMessage()).append("】");
        //显示第一条抛出异常位置信息
        StackTraceElement [] messages=this.getStackTrace();
        if(messages!=null && messages.length>0){
            errLog.append(LINE_SEPARATOR+"【抛出位置："+messages[0].toString()+"】");
        }
        return errLog.toString();
    }

    /**
     * 获得cause信息
     */
    public String getCauseInfo() {
        Throwable c = getCause();
        StringWriter sw = new StringWriter();
        if (c != null) {
            PrintWriter pw = new PrintWriter(sw);
            try {
                pw.print(LINE_SEPARATOR);
                c.printStackTrace(pw);
            } finally {
                pw.close();
            }
        }
        return sw.toString();
    }
    public ExceptionCode getCode() {
        return code;
    }

    public ExceptionLevel getLevel() {
        return level;
    }
}

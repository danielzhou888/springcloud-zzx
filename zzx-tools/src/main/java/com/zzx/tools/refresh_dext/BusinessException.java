package com.zzx.tools.refresh_dext;

/**
 * <pre>
 * The Class BusinessException.
 * 
 * Description:
 * 
 * author: 刘航
 * 
 * Revision History:
 * <who>   <when>   <what>
 * 刘航   2013-3-26
 * 
 * </pre>
 */
public class BusinessException extends RuntimeException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5333594641625781010L;
	
	/**
	 * Instantiates a new business exception.
	 * 
	 * @param message the message
	 * @author 刘航 2013-3-26
	 */
	public BusinessException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new business exception.
	 * 
	 * @param message the message
	 * @param e the e
	 * @author 刘航 2013-3-26
	 */
	public BusinessException(String message, Throwable e) {
		super(message, e);
	}
}

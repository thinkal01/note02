package com.note.controller.exception;

/**
 * <p>Description:系统自定义异常 </p>
 */
public class CustomException extends Exception {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5212079010855161498L;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    //异常信息
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

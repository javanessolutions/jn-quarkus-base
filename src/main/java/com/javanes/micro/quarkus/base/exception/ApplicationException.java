package com.javanes.micro.quarkus.base.exception;

import com.javanes.micro.quarkus.base.enums.AppExceptionEnum;

public class ApplicationException extends Exception{
    
    private AppExceptionEnum code;
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ApplicationException() {
        super(AppExceptionEnum.STATUS_GENERIC_ERROR.getMessage());
        code = AppExceptionEnum.STATUS_GENERIC_ERROR;
    }

    public ApplicationException(AppExceptionEnum code, String message) {
        super(message);
        this.code = code;
    }

    public ApplicationException(int code, String message, Throwable cause) {
        super(message, cause);
        code = AppExceptionEnum.STATUS_GENERIC_ERROR.getCode();
    }

    public ApplicationException(Throwable cause) {
        super(cause);
        code = AppExceptionEnum.STATUS_GENERIC_ERROR;
    }

    public AppExceptionEnum getCode() {
        return code;
    }

}

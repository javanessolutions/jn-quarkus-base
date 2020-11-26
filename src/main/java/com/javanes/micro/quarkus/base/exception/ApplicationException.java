package com.javanes.micro.quarkus.base.exception;

public class ApplicationException extends Exception{
    
    private int code;
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ApplicationException() {
        super("Algo salio mal.");
        code = 1;
    }

    public ApplicationException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ApplicationException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
        code = 1;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}

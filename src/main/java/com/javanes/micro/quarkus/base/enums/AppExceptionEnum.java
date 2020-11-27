package com.javanes.micro.quarkus.base.enums;

import javax.ws.rs.core.Response;

public enum AppExceptionEnum {
    STATUS_UNKNOWN(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), 0,"Caso de uso no contemplado en la aplicación."),
    STATUS_GENERIC_ERROR(Response.Status.BAD_REQUEST.getStatusCode(),1, "Error generico de la aplicación.");

    private final int httpCode;
    private final int code;
    private final String message;

    AppExceptionEnum(int httpCode, int code,String message){
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

    public int getHttpCode(){
        return httpCode;
    }
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

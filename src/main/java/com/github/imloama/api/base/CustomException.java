package com.github.imloama.api.base;

public class CustomException extends Exception {

    public CustomException(String msg){
        super(msg);
    }

    public CustomException(String msg, Throwable cause){
        super(msg, cause);
    }

}

package com.alephlabs.assignment.exception;

public class CommonException extends  Exception{

    public CommonException (String error , Throwable throwable)
    {
        super(error,throwable);
    }
}

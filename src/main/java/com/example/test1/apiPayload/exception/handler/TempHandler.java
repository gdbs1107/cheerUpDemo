package com.example.test1.apiPayload.exception.handler;


import com.example.test1.apiPayload.code.BaseErrorCode;
import com.example.test1.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}

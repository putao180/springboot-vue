package com.tp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
public class DefaulrException {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String esception(){
        return "forbiden";
    }
}

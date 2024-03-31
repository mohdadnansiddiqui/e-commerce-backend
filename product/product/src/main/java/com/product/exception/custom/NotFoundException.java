package com.product.exception.custom;

public class NotFoundException extends  RuntimeException{
    public NotFoundException(String msg){
        super(msg);
    }
}

package com.example.demo.common;


/**
 * 基础的常量code配置
 * @param
 */
public class BaseConstant<T> implements IBaseConstant<T> {

    private T codeValue;
    private String codeName;

    public BaseConstant(T codeValue, String codeName) {
        this.codeValue = codeValue;
        this.codeName = codeName;
    }

    @Override
    public T getCodeValue() {
        return codeValue;
    }

    public BaseConstant<T> setCodeValue(T codeValue) {
        this.codeValue = codeValue;
        return this;
    }

    @Override
    public String getCodeName() {
        return codeName;
    }

    public BaseConstant<T> setCodeName(String codeName) {
        this.codeName = codeName;
        return this;
    }
}

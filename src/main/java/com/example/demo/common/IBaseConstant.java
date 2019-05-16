package com.example.demo.common;

/**
 * 抽象数据字典和常量具有的特点
 * @param <T>
 */
public interface IBaseConstant<T> {
    /**
     * 得到当前数据字典的value
     * @return
     */
    T getCodeValue();
    /**
     * 得到当前数据字典的name
     * @return
     */
    String getCodeName();
}

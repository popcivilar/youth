package com.popcivilar.youth.youthbase.base.entity;

import java.io.Serializable;

/**
 * 与前端交互的结果集
 * @param <T>
 */
public class ModuleReturn<T> implements Serializable {

    private String code;//200:成功；非200 失败

    private T data;

    private String returnMsg;


    public ModuleReturn(String code, T data, String returnMsg) {
        this.code = code;
        this.data = data;
        this.returnMsg = returnMsg;
    }
    public ModuleReturn(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public static <T> ModuleReturn<T> success(T data,String returnMsg){
        return new ModuleReturn("200",data,returnMsg);
    }

    public static <T> ModuleReturn<T> success(T data){
        return new ModuleReturn("200",data);
    }

    public static <T> ModuleReturn<T> success(){
        return new ModuleReturn("200",null);
    }

    public static <T> ModuleReturn<T> fail(String returnMsg){
        return new ModuleReturn("200",null);
    }

    public static <T> ModuleReturn<T> fail(T data,String returnMsg){
        return new ModuleReturn("500",returnMsg);
    }

    public boolean isSuccess(){
        return this.code.equals("200");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}

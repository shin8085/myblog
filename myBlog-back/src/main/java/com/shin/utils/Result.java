package com.shin.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result {
    public int code; //200成功
    public String message;
    public Object data;

    public static Result success(int code,String message,Object data){
        return new Result(code,message,data);
    }
    public static Result success(String message, Object data){
        return success(200,message,data);
    }
    public static Result error(int code,String message,Object data){
        return new Result(code,message,data);
    }
    public static Result error(String message, Object data){
        return error(404,message,data);
    }
}

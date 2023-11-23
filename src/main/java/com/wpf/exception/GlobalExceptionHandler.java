package com.wpf.exception;

import com.wpf.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//定义全局异常处理器非常简单，就是定义一个类，
// 在类上加上一个注解@RestControllerAdvice， = @ControllerAdvice + @ResponseBody
// 加上这个注解就代表我们定义了一个全局异常处理器。
//@ExceptionHandler  //指定可以捕获哪种类型的异常进行处理
@RestControllerAdvice
public class GlobalExceptionHandler {
    //处理异常
    @ExceptionHandler(Exception.class)//指定能够处理的异常类型
    public Result exception(Exception ex){
        ex.printStackTrace();// 打印堆栈中的异常信息
        return Result.error("对不起，操作失败，请联系管理员");
    }
}

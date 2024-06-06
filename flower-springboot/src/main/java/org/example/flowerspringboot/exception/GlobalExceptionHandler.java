package org.example.flowerspringboot.exception;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.example.flowerspringboot.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result hanleException(Exception e){
        System.out.println(e);
        return Result.error(StringUtils.isNotEmpty(e.getMessage())?e.getMessage():"操作失败");
    }
}

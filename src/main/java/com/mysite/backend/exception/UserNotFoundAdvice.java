package com.mysite.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //모든 컨트롤러 클래스에 적용
public class UserNotFoundAdvice {

    @ResponseBody // 응답(response)시  자바객체를 http 응답 body 로 변환
    @ExceptionHandler(UserNotFoundException.class) //괄호안의 예외발생시 이 메소드에서 처리함 현재 ControllerAdvice가 적용되어 모든 컨트롤러에서 이 예외가 발생하면 여기서 처리됨
    @ResponseStatus(HttpStatus.NOT_FOUND)  //http 상태값을 404로 응답 한다
    public Map<String, String> exceptionHandler(UserNotFoundException exception) {
        Map<String, String> errorMap  = new HashMap<String, String>();
        errorMap.put("errorMessage", exception.getMessage());

        return errorMap ;
    }
}

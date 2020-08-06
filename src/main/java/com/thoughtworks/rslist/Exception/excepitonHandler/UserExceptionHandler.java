package com.thoughtworks.rslist.Exception.excepitonHandler;

import com.thoughtworks.rslist.controller.UserController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {UserController.class})
public class UserExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> DefinedRuleExceptionHanlder(Exception e){
        return ResponseEntity.badRequest().body(new Error("invalid user"));
    }


}
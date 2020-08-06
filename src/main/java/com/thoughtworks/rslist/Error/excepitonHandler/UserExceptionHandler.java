package com.thoughtworks.rslist.Error.excepitonHandler;

import com.thoughtworks.rslist.Error.Error;
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
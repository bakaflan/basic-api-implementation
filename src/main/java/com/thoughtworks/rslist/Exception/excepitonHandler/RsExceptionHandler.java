package com.thoughtworks.rslist.Exception.excepitonHandler;


import com.thoughtworks.rslist.Exception.*;
import com.thoughtworks.rslist.controller.RsController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.Error;

@ControllerAdvice(assignableTypes = {RsController.class})
public class RsExceptionHandler {

    @ExceptionHandler({RsNotValidException.class, IndexOutOfRange.class, RsException.class, LocalDateTimeException.class})
    public ResponseEntity<Error> rsExceptionHandler(Exception e){
        Error error = new Error(e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> DefinedRuleExceptionHanlder(Exception e){
        return ResponseEntity.badRequest().body(new Error("invalid param"));
    }

    @ExceptionHandler(UserNotExistedException.class)
    public ResponseEntity<Error> UserNotExistedExceptionHanlder(Exception e){
        return ResponseEntity.badRequest().body(new Error("create rs with non existed user"));
    }





}

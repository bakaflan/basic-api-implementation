package com.thoughtworks.rslist.Error.excepitonHandler;

import com.thoughtworks.rslist.Error.Error;
import com.thoughtworks.rslist.Error.IndexOutOfRange;
import com.thoughtworks.rslist.Error.RsNotValidException;
import com.thoughtworks.rslist.controller.RsController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {RsController.class})
public class RsExceptionHandler {

    @ExceptionHandler({RsNotValidException.class, IndexOutOfRange.class})
    public ResponseEntity<Error> rsExceptionHandler(Exception e){
        Error error = new Error(e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> DefinedRuleExceptionHanlder(Exception e){
        return ResponseEntity.badRequest().body(new Error("invalid param"));
    }

}

package com.thoughtworks.rslist.Exception.excepitonHandler;


import com.thoughtworks.rslist.Exception.IndexOutOfRange;
import com.thoughtworks.rslist.Exception.RsNotValidException;
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

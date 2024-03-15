package ripemango.springframework.spring6restmvc.controller;

import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.spi.Resolver;

/**
 * Author: Ripemango
 * Date: 2024/3/15
 */
@ControllerAdvice
public class CustomerErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){
        return  ResponseEntity.badRequest().body(exception.getBindingResult().getFieldErrors());
    }
}

package ripemango.springframework.spring6restmvc.controller;

import jakarta.persistence.Entity;
import jakarta.validation.ConstraintViolationException;
import org.h2.mvstore.tx.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.spi.Resolver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author: Ripemango
 * Date: 2024/3/15
 */
@ControllerAdvice
public class CustomerErrorController {

    @ExceptionHandler
    ResponseEntity handleJPAViolation(TransactionSystemException exception){
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.badRequest();

       if(exception.getCause().getCause() instanceof ConstraintViolationException){
            ConstraintViolationException ve =(ConstraintViolationException) exception.getCause().getCause();

            List errors  = ve.getConstraintViolations().stream()
                    .map(constraintViolation -> {
                        Map<String,String> errMap = new HashMap<>();
                        errMap.put(constraintViolation.getPropertyPath().toString(),
                                constraintViolation.getMessage());
                        return errMap;
                    }).collect(Collectors.toList());
            return responseEntity.body(errors);
        }

        return  responseEntity.build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){

        List errorList = exception.getFieldErrors().stream()
                .map(fieldError ->{
                    Map<String,String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
                    return errorMap;
                }).collect(Collectors.toList());

        return  ResponseEntity.badRequest().body(errorList);
    }
}

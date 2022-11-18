//package com.mta.jwt.demo.exception;
//
//import com.mta.jwt.demo.payload.response.ErrorMessage;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ControllerAdvice
//@ResponseBody
//public class ControllerExceptionHandler {
//    @ExceptionHandler(value = {ResourceNotFoundException.class, CertainException.class})
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
//        ErrorMessage message = new ErrorMessage(
//                status,
//                date,
//                ex.getMessage(),
//                description);
//
//        return message;
//    }
//}

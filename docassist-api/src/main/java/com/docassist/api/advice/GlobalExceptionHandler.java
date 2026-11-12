package com.docassist.api.advice;

import com.docassist.commons.constants.ResponseCode;
import com.docassist.commons.constants.ResponseDesc;
import com.docassist.commons.exception.DocAssistException;
import com.docassist.commons.exception.ValidationException;
import com.docassist.commons.utils.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException  ex) {
        logger.debug("Request field validation failed with {} field errors", ex.getBindingResult().getFieldErrorCount());
        return new ResponseEntity<>(new ServiceResponse<>(ResponseCode.INVALID_FIELDS,false, ResponseDesc.INVALID_FIELDS,null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ServiceResponse> handleValidationException(ValidationException ex){
        logger.debug("Request validation failed with code {}", ex.getCode());
        return new ResponseEntity<>(new ServiceResponse<>(ResponseCode.REQUEST_VALIDATION_FAILED,false, ResponseDesc.REQUEST_VALIDATION_FAILED, null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ServiceResponse> handleMsgNotReadableException(HttpMessageNotReadableException ex){
        logger.error("HTTP Handle Msg Not Readable Invoked!");
        return new ResponseEntity<>(new ServiceResponse<>(ResponseCode.INVALID_REQUEST_BODY,false, ResponseDesc.INVALID_REQUEST_BODY, null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ServiceResponse> handleNoResourceFoundException(NoResourceFoundException ex){
        logger.debug("Requested resource was not found. Code: {}", ResponseCode.NO_RESOURCE_FOUND);
        return new ResponseEntity<>(new ServiceResponse<>(ResponseCode.NO_RESOURCE_FOUND ,false, ResponseDesc.NO_RESOURCE_FOUND, null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DocAssistException.class)
    public ResponseEntity<ServiceResponse> handleDocAssistException(DocAssistException ex){
        logger.error("DocAssist operation failed with code {}", ex.getCode());
        return new ResponseEntity<>(new ServiceResponse<>(ResponseCode.INTERNAL_SERVER_ERROR, false, ResponseDesc.INTERNAL_SERVER_ERROR, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServiceResponse> handleUnexpectedException(Exception ex) throws Exception {
        if (ex instanceof ErrorResponse) { throw ex;}
        logger.error("Unexpected API failure of type {}", ex.getClass().getName());
        return new ResponseEntity<>(new ServiceResponse<>(ResponseCode.UNEXPECTED_ERROR, false, ResponseDesc.UNEXPECTED_ERROR, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

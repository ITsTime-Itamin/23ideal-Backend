package com.example.itaminbackend.global.exception;

import com.example.itaminbackend.global.dto.ExceptionResponse;
import com.example.itaminbackend.infra.file.exception.FileExtensionException;
import com.example.itaminbackend.infra.file.exception.FileLoadFailedException;
import com.example.itaminbackend.infra.file.exception.FileSaveFailedException;
import com.example.itaminbackend.infra.file.exception.ImageNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Global Domain Exception
     */

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), Arrays.asList(ex.getMessage()));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<Object> HttpClientErrorException(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.toString(), Arrays.asList(ex.getMessage().substring(4)));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleRunTimeExceptions(Exception ex, WebRequest request) {


        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), Arrays.asList(ex.getMessage()));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        ExceptionResponse exceptionResponse = new ExceptionResponse(status.toString(), errors);

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * File Domain Exception
     */

    @ExceptionHandler(FileExtensionException.class)
    public final ResponseEntity<Object> handleFileExtensionException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.toString(), Arrays.asList(ex.getMessage()));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileLoadFailedException.class)
    public final ResponseEntity<Object> handleFileLoadFailedException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.toString(), Arrays.asList(ex.getMessage()));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileSaveFailedException.class)
    public final ResponseEntity<Object> handleFileSaveFailedException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.toString(), Arrays.asList(ex.getMessage()));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public final ResponseEntity<Object> handleImageNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.toString(), Arrays.asList(ex.getMessage()));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}

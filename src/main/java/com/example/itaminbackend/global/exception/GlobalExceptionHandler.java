package com.example.itaminbackend.global.exception;

import com.example.itaminbackend.domain.user.exception.NotFoundEmailException;
import com.example.itaminbackend.global.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.itaminbackend.domain.user.constant.UserConstants.UserExceptionList.NOT_FOUND_EMAIL;
import static java.util.Objects.requireNonNull;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String LOG_FORMAT = "Class : {}, Code : {}, Message : {}";
    private static final String INTERNAL_SERVER_ERROR_CODE = "S0001";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        String errorCode = requireNonNull(e.getFieldError())
                .getDefaultMessage();
        ApiErrorResponse exceptionResponse = new ApiErrorResponse(errorCode, e.getMessage());
        log.warn(LOG_FORMAT, e.getClass().getSimpleName(), errorCode, "@Valid");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(exceptionResponse);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiErrorResponse> applicationException(ApplicationException e) {
        String errorCode = e.getErrorCode();
        log.warn(
                LOG_FORMAT,
                e.getClass().getSimpleName(),
                errorCode,
                e.getMessage()
        );
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ApiErrorResponse(errorCode, e.getMessage()));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiErrorResponse> dataAccessException(DataAccessException e) {
        log.error(
                LOG_FORMAT,
                e.getClass().getSimpleName(),
                INTERNAL_SERVER_ERROR_CODE,
                e.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse(INTERNAL_SERVER_ERROR_CODE, "데이터 접근 에러가 발생했습니다."));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> runtimeException(RuntimeException e) {
        log.error(
                LOG_FORMAT,
                e.getClass().getSimpleName(),
                INTERNAL_SERVER_ERROR_CODE,
                e.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse(INTERNAL_SERVER_ERROR_CODE, "런타임 에러가 발생했습니다."));
    }


    /**
     * Security
     */

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ApiErrorResponse> internalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        log.error(
                LOG_FORMAT,
                e.getClass().getSimpleName(),
                NOT_FOUND_EMAIL.getErrorCode(),
                e.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(NOT_FOUND_EMAIL.getErrorCode(), e.getMessage()));
    }
}

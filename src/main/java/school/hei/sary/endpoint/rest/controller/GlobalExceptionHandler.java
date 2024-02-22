package school.hei.sary.endpoint.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import school.hei.sary.exception.ApiErrorResponse;
import school.hei.sary.exception.ApiException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler({ApiException.class})
  public ResponseEntity<ApiErrorResponse> handleRuntimeException(ApiException error) {
    return new ResponseEntity<>(
        new ApiErrorResponse(error.getMessage(), error.getStatus()), error.getStatus());
  }
}

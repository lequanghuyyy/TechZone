//package com.springboot.shopbubu.exception;
//
//import com.springboot.shopbubu.exception.notFoundException.*;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//// global exception
//@RestControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        Map<String, Object> objectBody = new LinkedHashMap<>();
//        objectBody.put("Current Timestamp", new Date());
//        List<String> exceptionalErrors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getField() + ":" + x.getDefaultMessage())
//                .toList();
//
//        objectBody.put("Errors", exceptionalErrors);
//        return ResponseEntity.badRequest().body(objectBody);
//    }
//
//
//    @ExceptionHandler(CommonException.class)
//    public ResponseEntity<String> handleExceptionA(Exception e) {
//        return ResponseEntity.status(432).body(e.getMessage());
//    }
//
//
//    /**
//     * Handle AlreadyExistsException: triggers when a duplicate resource is being created.
//     *
//     * @param ex      the AlreadyExistsException
//     * @param request the current web request
//     * @return a ResponseEntity with the error details and HttpStatus.CONFLICT
//     */
//
//    @ExceptionHandler(AlreadyExistsException.class)
//    public ResponseEntity<Object> handleAlreadyExistsException(AlreadyExistsException ex, WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", HttpStatus.CONFLICT.value());
//        body.put("message", ex.getMessage());
//
//        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
//    }
//
//    /**
//     * Handle Invalid username or password: triggers when invalid credentials are provided during login.
//     *
//     * @param ex      the InvalidCredentialsException
//     * @param request the current web request
//     * @return a ResponseEntity with the error details and HttpStatus.UNAUTHORIZED
//     */
//    @ExceptionHandler(InvalidCredentialsException.class)
//    public ResponseEntity<Object> handleInvalidCredentialsException(InvalidCredentialsException ex, WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", HttpStatus.UNAUTHORIZED.value());
//        body.put("message", "Invalid credentials");
//        body.put("details", ex.getMessage());
//
//        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", HttpStatus.FORBIDDEN.value());
//        body.put("error", HttpStatus.FORBIDDEN.getReasonPhrase());
//        body.put("message", "You do not have sufficient permissions to access this resource.");
//        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
//    }
//    @ExceptionHandler(NotFoundCartException.class)
//    public ResponseEntity<Object> handleNotFoundCartException(NotFoundCartException ex) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", HttpStatus.NOT_FOUND.value());
//        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
//        body.put("message", ex.getMessage());
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(NotFoundCategoryException.class)
//    public ResponseEntity<Object> handleNotFoundCategoryException(NotFoundCategoryException ex) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", HttpStatus.NOT_FOUND.value());
//        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
//        body.put("message", ex.getMessage());
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(NotFoundCustomerException.class)
//    public ResponseEntity<Object> handleNotFoundCustomerException(NotFoundCustomerException ex) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", HttpStatus.NOT_FOUND.value());
//        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
//        body.put("message", ex.getMessage());
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(NotFoundOrderException.class)
//    public ResponseEntity<Object> handleNotFoundOrderException(NotFoundOrderException ex) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", HttpStatus.NOT_FOUND.value());
//        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
//        body.put("message", ex.getMessage());
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(NotFoundProductException.class)
//    public ResponseEntity<Object> handleNotFoundProductException(NotFoundProductException ex) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", HttpStatus.NOT_FOUND.value());
//        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
//        body.put("message", ex.getMessage());
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//    }
//
//
//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", HttpStatus.FORBIDDEN.value());
//        body.put("error", HttpStatus.FORBIDDEN.getReasonPhrase());
//        body.put("message", "You do not have sufficient permissions to access this resource.");
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler(OrderAlreadyShippedException.class)
//    public ResponseEntity<Object> handleOrderAlreadyShippedException(OrderAlreadyShippedException ex) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", HttpStatus.CONFLICT.value());
//        body.put("error", HttpStatus.CONFLICT.getReasonPhrase());
//        body.put("message", "Order already shipped.");
//        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
//    }
//
//
//
//    // Nên bắt cả Exception.class
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleUnwantedException(Exception e) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        body.put("message", "system error");
//        body.put("details", e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
//    }
//}

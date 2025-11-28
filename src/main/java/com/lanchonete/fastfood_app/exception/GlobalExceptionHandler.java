package com.lanchonete.fastfood_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarValidacao(MethodArgumentNotValidException e) {

        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("erro", "Campos inválidos");
        body.put("timestamp", LocalDateTime.now());

        Map<String, String> erros = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(err -> {
            erros.put(err.getField(), err.getDefaultMessage());
        });

        body.put("detalhes", erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> tratarResponseStatus(ResponseStatusException e) {

        Map<String, Object> body = new HashMap<>();
        body.put("status", e.getStatusCode().value());
        body.put("erro", e.getReason());
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, e.getStatusCode());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> tratarRuntime(RuntimeException e) {

        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("erro", e.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}

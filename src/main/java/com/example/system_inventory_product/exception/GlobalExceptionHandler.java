package com.example.system_inventory_product.exception;

import com.example.system_inventory_product.controller.api.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        StandardResponse response = new StandardResponse(ex.getMessage(), "Recurso no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<StandardResponse> handleInvalidDataException(InvalidDataException ex) {
        StandardResponse response = new StandardResponse(ex.getMessage(), "Datos inválidos");
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(EmptyDataException.class)
    public ResponseEntity<StandardResponse> handleEmptyDataException(EmptyDataException ex) {
        StandardResponse response = new StandardResponse(ex.getMessage(), "Datos vacíos");
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<StandardResponse> handleInternalErrorException(InternalErrorException ex) {
        StandardResponse response = new StandardResponse(ex.getMessage(), "Error interno del servidor");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        StandardResponse response = new StandardResponse("Valor de parámetro no válido: " + ex.getValue() + ". Proporcione un valor entero.", "Error de tipo");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse> handleException(Exception ex) {
        StandardResponse response = new StandardResponse("Error interno del servidor: " + ex.getMessage(), "Error inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

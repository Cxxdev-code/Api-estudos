package com.example.demo.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.tarefas.TarefaDescricaoException;
import com.example.demo.exception.tarefas.TarefaStatusException;
import com.example.demo.exception.tarefas.TarefaTituloException;
import com.example.demo.exception.tarefas.TarefasNotFoundException;
import com.example.demo.exception.tarefas.TarefasNotFoundStatusException;
import com.example.demo.exception.usuarios.UsuarioNameException;
import com.example.demo.exception.usuarios.UsuarioNotFoundException;
import com.example.demo.exception.usuarios.UsuarioNotFoundIdadeException;
import com.example.demo.exception.usuarios.UsuariosNotFoundNameException;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ErrorResponse handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
       return errorResponse;
    }

    @ExceptionHandler(UsuarioNameException.class)
    public ErrorResponse handleUsuarioNameException(UsuarioNameException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return ErrorResponse.builder()
                .message(errorMessage)
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }
    
    @ExceptionHandler(UsuariosNotFoundNameException.class)
    public ErrorResponse handleUsuariosNotFoundException(UsuariosNotFoundNameException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        return errorResponse;
    }

    @ExceptionHandler(UsuarioNotFoundIdadeException.class)
    public ErrorResponse handleUsuarioNotFoundIdadeException(UsuarioNotFoundIdadeException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        return errorResponse;
    }

    @ExceptionHandler({TarefaTituloException.class, TarefaDescricaoException.class, TarefaStatusException.class})
    public ErrorResponse handleTarefaValidationException(RuntimeException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(TarefasNotFoundException.class)
    public ErrorResponse handleTarefasNotFoundException(TarefasNotFoundException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
    }

    @ExceptionHandler(TarefasNotFoundStatusException.class)
    public ErrorResponse handleTarefasNotFoundStatusException(TarefasNotFoundStatusException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
    }

}

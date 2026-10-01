package com.example.demo.exception.tarefas;

public class TarefasNotFoundStatusException extends RuntimeException {

    public TarefasNotFoundStatusException(String message) {
        super(message);
    }
}
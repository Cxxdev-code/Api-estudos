package com.example.demo.exception.tarefas;

public class TarefasNotFoundException extends RuntimeException {

    public TarefasNotFoundException(String message) {
        super(message);
    }
}
package com.example.demo.exception.usuarios;

public class UsuariosNotFoundNameException extends RuntimeException {

    public UsuariosNotFoundNameException(String message) {
        super(message);
    }
}
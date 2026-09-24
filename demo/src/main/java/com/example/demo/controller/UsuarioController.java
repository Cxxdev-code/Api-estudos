package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dtos.UsuarioDtoResponse;
import com.example.demo.Dtos.UsuariosDtoRequest;
import com.example.demo.service.UsuarioService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor 
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @GetMapping()
    public List<UsuarioDtoResponse> todosUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping()
    public UsuarioDtoResponse criarUsuario(@RequestBody UsuariosDtoRequest UsuarioDtoRequest) {
        return usuarioService.criarUsuario(UsuarioDtoRequest);
    }

}

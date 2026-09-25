package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UsuarioDtoResponse;
import com.example.demo.dtos.UsuariosDtoRequest;
import com.example.demo.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public UsuarioDtoResponse criarUsuario(@Valid @RequestBody UsuariosDtoRequest usuarioDtoRequest) {
        return usuarioService.criarUsuario(usuarioDtoRequest);
    }

    @GetMapping("/idade")
    public List<UsuarioDtoResponse> UsuariosPorIdade(@RequestParam Integer idade) {
        return usuarioService.listarUsuariosPorIdade(idade);
    }
    

    @PutMapping("/{id}")
    public UsuarioDtoResponse atualizarUsuario(@PathVariable Long id,@Valid @RequestBody UsuariosDtoRequest usuarioDtoRequest) {
        return usuarioService.atualizarUsuario(id, usuarioDtoRequest);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }


}

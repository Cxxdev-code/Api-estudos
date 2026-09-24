package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Dtos.UsuarioDtoResponse;
import com.example.demo.Dtos.UsuariosDtoRequest;
import com.example.demo.model.UsuarioEntity;
import com.example.demo.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioDtoResponse criarUsuario(UsuariosDtoRequest usuarioRequest) {

        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .nome(usuarioRequest.getNome())
                .email(usuarioRequest.getEmail())
                .idade(usuarioRequest.getIdade())
                .build();
                
        usuarioRepository.save(usuarioEntity);

        return UsuarioDtoResponse.builder()
                .nome(usuarioEntity.getNome())
                .email(usuarioEntity.getEmail())
                .idade(usuarioEntity.getIdade())
                .build();
    }

    public List<UsuarioDtoResponse> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioEntity -> UsuarioDtoResponse.builder()
                        .nome(usuarioEntity.getNome())
                        .email(usuarioEntity.getEmail())
                        .idade(usuarioEntity.getIdade())
                        .build())
                .toList();
    }
}
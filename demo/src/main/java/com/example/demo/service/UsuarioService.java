package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.UsuarioDtoResponse;
import com.example.demo.dtos.UsuariosDtoRequest;
import com.example.demo.model.UsuarioEntity;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.mapper.MapperUsuario;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final MapperUsuario mapperUsuario;

    public UsuarioDtoResponse criarUsuario(UsuariosDtoRequest usuarioRequest) {
        UsuarioEntity usuarioEntity = mapperUsuario.toEntity(usuarioRequest);
        usuarioRepository.save(usuarioEntity);
        return mapperUsuario.toResponse(usuarioEntity);
    }

    public List<UsuarioDtoResponse> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(mapperUsuario::toResponse)
                .toList();
    }

    public UsuarioDtoResponse atualizarUsuario(Long id, UsuariosDtoRequest usuarioRequest) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        mapperUsuario.updateEntityFromDto(usuarioRequest, usuarioEntity);
        usuarioRepository.save(usuarioEntity);

        return mapperUsuario.toResponse(usuarioEntity);
    }

    public void deletarUsuario(Long id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(usuarioEntity);
    }

    public List<UsuarioDtoResponse> listarUsuariosPorIdade(Integer idade) {
        return usuarioRepository.findByIdade(idade).stream()
                .map(mapperUsuario::toResponse)
                .toList();
    }
}
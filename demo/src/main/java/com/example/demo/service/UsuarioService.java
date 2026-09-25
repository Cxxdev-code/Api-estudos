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

    public UsuarioDtoResponse atualizarUsuario(Long id, UsuariosDtoRequest usuarioRequest) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioEntity.setNome(usuarioRequest.getNome());
        usuarioEntity.setEmail(usuarioRequest.getEmail());
        usuarioEntity.setIdade(usuarioRequest.getIdade());

        usuarioRepository.save(usuarioEntity);

        return UsuarioDtoResponse.builder()
                .nome(usuarioEntity.getNome())
                .email(usuarioEntity.getEmail())
                .idade(usuarioEntity.getIdade())
                .build();
    }

        public void deletarUsuario(Long id) {
                UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
                usuarioRepository.delete(usuarioEntity);
        }
}
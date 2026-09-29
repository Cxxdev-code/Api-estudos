package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.usuarios.UsuarioDtoResponse;
import com.example.demo.dtos.usuarios.UsuariosDtoRequest;
import com.example.demo.exception.UsuarioNameException;
import com.example.demo.exception.UsuarioNotFoundException;
import com.example.demo.exception.UsuarioNotFoundIdadeException;
import com.example.demo.exception.UsuariosNotFoundNameException;
import com.example.demo.model.UsuariosEntity;
import com.example.demo.repository.UsuariosRepository;
import com.example.demo.service.mapper.MapperUsuario;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class UsuarioService {
    
    private final UsuariosRepository usuarioRepository;
    private final MapperUsuario mapperUsuario;

    public UsuarioDtoResponse criarUsuario(UsuariosDtoRequest usuarioRequest) {

        if (usuarioRepository.existsByNome(usuarioRequest.getNome())) {
            throw new UsuarioNameException("Usuário com nome " + usuarioRequest.getNome() + " já existe");
        }
        
        UsuariosEntity usuarioEntity = mapperUsuario.toEntity(usuarioRequest);
        usuarioRepository.save(usuarioEntity);

        return mapperUsuario.toResponse(usuarioEntity);
    }

    public List<UsuarioDtoResponse> listarUsuarios() {

        return usuarioRepository.findAll().stream()
                .map(mapperUsuario::toResponse)
                .toList();
    }

    public UsuarioDtoResponse atualizarUsuario(Long id, UsuariosDtoRequest usuarioRequest) {

        UsuariosEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));

        mapperUsuario.updateEntityFromDto(usuarioRequest, usuarioEntity);
        usuarioRepository.save(usuarioEntity);

        return mapperUsuario.toResponse(usuarioEntity);
    }

    public void deletarUsuario(Long id) {
        UsuariosEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));

        usuarioRepository.delete(usuarioEntity);
    }

    public List<UsuarioDtoResponse> listarUsuariosPorIdade(Integer idade) {
        return usuarioRepository.findByIdade(idade)
                .orElseThrow(() -> new UsuarioNotFoundIdadeException("Usuário com idade " + idade + " não encontrado"))
                .stream()
                .map(mapperUsuario::toResponse)
                .toList();
    }

    public List<UsuarioDtoResponse> listarUsuariosPorNome(String nome) {
        return usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new UsuariosNotFoundNameException("Usuário com nome " + nome + " não encontrado"))
                .stream()
                .map(mapperUsuario::toResponse)
                .toList();
    }
}
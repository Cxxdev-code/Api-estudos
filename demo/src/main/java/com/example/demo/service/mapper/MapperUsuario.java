package com.example.demo.service.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dtos.UsuarioDtoResponse;
import com.example.demo.dtos.UsuariosDtoRequest;
import com.example.demo.model.UsuariosEntity;

@Component
public class MapperUsuario {

    /**
     * Converte um DTO de requisição (UsuariosDtoRequest) para a entidade do banco de dados (UsuarioEntity).
     */
    public UsuariosEntity toEntity(UsuariosDtoRequest request) {
        if (request == null) {
            return null;
        }

        return UsuariosEntity.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .idade(request.getIdade())
                .build();
    }

    /**
     * Converte a entidade (UsuarioEntity) para o DTO de resposta (UsuarioDtoResponse).
     */
    public UsuarioDtoResponse toResponse(UsuariosEntity entity) {
        if (entity == null) {
            return null;
        }

        return UsuarioDtoResponse.builder()
                .nome(entity.getNome())
                .email(entity.getEmail())
                .idade(entity.getIdade())
                .build();
    }

    /**
     * Converte diretamente um DTO de requisição (UsuariosDtoRequest) para o DTO de resposta (UsuarioDtoResponse).
     */
    public UsuarioDtoResponse toResponse(UsuariosDtoRequest request) {
        if (request == null) {
            return null;
        }

        return UsuarioDtoResponse.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .idade(request.getIdade())
                .build();
    }

    /**
     * Atualiza os campos de uma UsuarioEntity existente a partir dos dados do DTO de requisição.
     */
    public void updateEntityFromDto(UsuariosDtoRequest request, UsuariosEntity entity) {
        if (request == null || entity == null) {
            return;
        }

        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setIdade(request.getIdade());
    }
}

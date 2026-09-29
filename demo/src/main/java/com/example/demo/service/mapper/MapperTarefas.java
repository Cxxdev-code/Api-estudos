package com.example.demo.service.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dtos.tarefas.TarefasDtoRequest;
import com.example.demo.dtos.tarefas.TarefasDtoResponse;
import com.example.demo.model.TarefasEntity;
import com.example.demo.model.UsuariosEntity;

@Component
public class MapperTarefas {

	public List<TarefasDtoResponse> toResponse(List<TarefasEntity> entities) {
		if (entities == null) {
			return null;
		}

		return entities.stream()
				.map(this::toResponse)
				.toList();
	}

	public TarefasEntity toEntity(TarefasDtoRequest request, UsuariosEntity usuario) {
		if (request == null) {
			return null;
		}

		return TarefasEntity.builder()
				.titulo(request.getTitulo())
				.descricao(request.getDescricao())
				.status(request.getStatus())
				.usuario(usuario)
				.build();
	}

	public TarefasDtoResponse toResponse(TarefasEntity entity) {
		if (entity == null) {
			return null;
		}

		return TarefasDtoResponse.builder()
				.titulo(entity.getTitulo())
				.descricao(entity.getDescricao())
				.status(entity.getStatus())
				.build();
	}

	public TarefasDtoResponse toResponse(TarefasDtoRequest request) {
		if (request == null) {
			return null;
		}

		return TarefasDtoResponse.builder()
				.titulo(request.getTitulo())
				.descricao(request.getDescricao())
				.status(request.getStatus())
				.build();
	}

	public void updateEntityFromDto(TarefasDtoRequest request, TarefasEntity entity) {
		if (request == null || entity == null) {
			return;
		}

		entity.setTitulo(request.getTitulo());
		entity.setDescricao(request.getDescricao());
		entity.setStatus(request.getStatus());
	}
}

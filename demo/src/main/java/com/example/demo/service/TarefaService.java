package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.tarefas.TarefasDtoRequest;
import com.example.demo.dtos.tarefas.TarefasDtoResponse;
import com.example.demo.exception.tarefas.TarefaDescricaoException;
import com.example.demo.exception.tarefas.TarefaStatusException;
import com.example.demo.exception.tarefas.TarefaTituloException;
import com.example.demo.exception.tarefas.TarefasNotFoundException;
import com.example.demo.exception.tarefas.TarefasNotFoundStatusException;
import com.example.demo.exception.usuarios.UsuarioNotFoundException;
import com.example.demo.model.Status;
import com.example.demo.model.TarefasEntity;
import com.example.demo.model.UsuariosEntity;
import com.example.demo.repository.TarefasRepository;
import com.example.demo.repository.UsuariosRepository;
import com.example.demo.service.mapper.MapperTarefas;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class TarefaService {
    
    private final TarefasRepository tarefasRepository;
    private final UsuariosRepository usuarioRepository;
    private final MapperTarefas mapperTarefas;

    public List<TarefasDtoResponse> listarTarefas(Long usuarioId) {
        List<TarefasEntity> tarefas = tarefasRepository.findByUsuario_Id(usuarioId);
        if (tarefas.isEmpty()) {
            throw new TarefasNotFoundException("Nenhuma tarefa encontrada para o usuário com ID: " + usuarioId);
        }
        return mapperTarefas.toResponse(tarefas);
    }

    public TarefasDtoResponse criarTarefa(TarefasDtoRequest request, Long usuarioId) {
        validarRequest(request);

        UsuariosEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado com o ID: " + usuarioId));

        TarefasEntity tarefa = mapperTarefas.toEntity(request, usuario);
        return mapperTarefas.toResponse(tarefasRepository.save(tarefa));
    }

    public List<TarefasDtoResponse> listarTarefasPorStatus(Long usuarioId, Status status) {
        List<TarefasEntity> tarefas;
        if (status != null) {
            tarefas = tarefasRepository.findByUsuario_IdAndStatus(usuarioId, status);
            if (tarefas.isEmpty()) {
                throw new TarefasNotFoundStatusException(
                        "Nenhuma tarefa encontrada com o status " + status + " para o usuário com ID: " + usuarioId);
            }
        } else {
            tarefas = tarefasRepository.findByUsuario_Id(usuarioId);
            if (tarefas.isEmpty()) {
                throw new TarefasNotFoundException("Nenhuma tarefa encontrada para o usuário com ID: " + usuarioId);
            }
        }
        return mapperTarefas.toResponse(tarefas);
    }

    private void validarRequest(TarefasDtoRequest request) {
        if (request == null || request.getTitulo() == null || request.getTitulo().isBlank()) {
            throw new TarefaTituloException("O título da tarefa é obrigatório");
        }
        if (request.getTitulo().length() < 5 || request.getTitulo().length() > 150) {
            throw new TarefaTituloException("O título deve ter entre 5 e 150 caracteres");
        }
        if (request.getDescricao() == null || request.getDescricao().isBlank()) {
            throw new TarefaDescricaoException("A descrição da tarefa é obrigatória");
        }
        if (request.getDescricao().length() < 5 || request.getDescricao().length() > 1000) {
            throw new TarefaDescricaoException("A descrição deve ter entre 5 e 1000 caracteres");
        }
        if (request.getStatus() == null) {
            throw new TarefaStatusException("O status da tarefa é obrigatório");
        }
    }


}

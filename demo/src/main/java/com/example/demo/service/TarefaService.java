package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.tarefas.TarefasDtoRequest;
import com.example.demo.dtos.tarefas.TarefasDtoResponse;
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


    public TarefasDtoResponse atualizarTarefa(Long tarefaId, Status status) {

            TarefasEntity tarefaExistente = tarefasRepository.findById(tarefaId)
                    .orElseThrow(() -> new TarefasNotFoundException("Tarefa não encontrada com o ID: " + tarefaId));

            tarefaExistente.setStatus(status);
            return mapperTarefas.toResponse(tarefasRepository.save(tarefaExistente));
        }


    public void deletarTarefa(Long tarefaId) {
        TarefasEntity tarefaExistente = tarefasRepository.findById(tarefaId)
                .orElseThrow(() -> new TarefasNotFoundException("Tarefa não encontrada com o ID: " + tarefaId));

        tarefasRepository.delete(tarefaExistente);
    }

}

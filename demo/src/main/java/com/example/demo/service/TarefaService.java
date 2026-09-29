package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.tarefas.TarefasDtoRequest;
import com.example.demo.dtos.tarefas.TarefasDtoResponse;
import com.example.demo.exception.UsuarioNotFoundException;
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
        return mapperTarefas.toResponse(tarefas);
    }

    public TarefasDtoResponse criarTarefa(TarefasDtoRequest request, Long usuarioId) {
    UsuariosEntity usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado com o ID: " + usuarioId));

    TarefasEntity tarefa = mapperTarefas.toEntity(request, usuario);
    return mapperTarefas.toResponse(tarefasRepository.save(tarefa));
}
}

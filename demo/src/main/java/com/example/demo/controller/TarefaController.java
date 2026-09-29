package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.tarefas.TarefasDtoResponse;
import com.example.demo.dtos.tarefas.TarefasDtoRequest;
import com.example.demo.service.TarefaService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/tarefas/{usuarioId}")
@AllArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public TarefasDtoResponse criarTarefa(@PathVariable Long usuarioId, @RequestBody TarefasDtoRequest request) {
        return tarefaService.criarTarefa(request, usuarioId);
    }
    
    
    @GetMapping
    public List<TarefasDtoResponse> listarTarefas(@PathVariable Long usuarioId) {
        return tarefaService.listarTarefas(usuarioId);


    }
}

package com.example.demo.dtos.tarefas;

import com.example.demo.model.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder 
@Getter 
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TarefasDtoRequest {
    
    private String titulo;

    private String descricao;

    private Status status;
}

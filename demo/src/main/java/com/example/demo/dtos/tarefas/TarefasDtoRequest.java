package com.example.demo.dtos.tarefas;

import com.example.demo.model.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "O título da tarefa é obrigatório")
    @Size(min = 5, max = 150, message = "O título deve ter entre 5 e 150 caracteres")
    private String titulo;

    @NotBlank(message = "A descrição da tarefa é obrigatória")
    @Size(min = 5, max = 1000, message = "A descrição deve ter entre 5 e 1000 caracteres")
    private String descricao;

    @NotNull(message = "O status da tarefa é obrigatório")
    private Status status;
}

package com.example.demo.dtos.tarefas;

import com.example.demo.model.Status;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDtoRequest {

    @NotNull(message = "O status da tarefa é obrigatório")
    private Status status;
}

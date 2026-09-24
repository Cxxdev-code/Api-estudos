package com.example.demo.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Builder 
@Getter 
@NoArgsConstructor
@AllArgsConstructor
@Setter 
public class UsuariosDtoRequest {
    
    @NotBlank(message = "O nome do usuário é obrigatório")
    private String nome;

    @Email(message = "O email do usuário deve ser válido")
    private String email;

    @NotNull(message = "A idade do usuário é obrigatória")
    private Integer idade;

}

package com.example.demo.dtos.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class UsuarioDtoResponse {
    @NotBlank(message = "O nome do usuário é obrigatório")
    @Size(min = 4, max = 150, message = "O nome deve ter entre 4 e 150 caracteres")
    private String nome;

    @NotBlank(message = "O email do usuário é obrigatório")
    @Email(message = "O email do usuário deve ser válido")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    private String email;

    @NotNull(message = "A idade do usuário é obrigatória")
    @Min(value = 0, message = "A idade não pode ser negativa")
    @Max(value = 130, message = "A idade informada é inválida")
    private Integer idade;
}

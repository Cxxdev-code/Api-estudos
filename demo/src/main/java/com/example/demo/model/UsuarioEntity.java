package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotBlank(message = "O nome do usuário é obrigatório")
    @Size(min = 4, max = 150, message = "O nome deve ter entre 4 e 150 caracteres")
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @NotNull(message = "A idade do usuário é obrigatória")
    @Min(value = 0, message = "A idade não pode ser negativa")
    @Max(value = 130, message = "A idade informada é inválida")
    @Column(name = "idade", nullable = false)
    private Integer idade;

    @NotBlank(message = "O email do usuário é obrigatório")
    @Email(message = "O email do usuário deve ser válido")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;
}


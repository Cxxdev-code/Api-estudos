package com.example.demo.dtos;

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
    private String nome;

    private String email;
    
    private Integer idade;
}

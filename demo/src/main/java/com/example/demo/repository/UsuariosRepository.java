package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.UsuariosEntity;


public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Long> {
    public Optional<List<UsuariosEntity>> findByIdade(Integer idade);

    public boolean existsByNome(String nome);

    public Optional<List<UsuariosEntity>> findByNome(String nome);
}

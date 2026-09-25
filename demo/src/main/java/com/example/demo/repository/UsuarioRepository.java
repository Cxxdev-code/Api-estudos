package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.UsuarioEntity;


public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    
    public List<UsuarioEntity> findAll();

    public List<UsuarioEntity> findByIdade(Integer idade);

}

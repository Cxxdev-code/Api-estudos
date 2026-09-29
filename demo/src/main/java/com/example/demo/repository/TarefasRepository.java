package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.TarefasEntity;

import java.util.List;

public interface TarefasRepository extends JpaRepository<TarefasEntity, Long> {

	List<TarefasEntity> findByUsuario_Id(Long usuarioId);
}

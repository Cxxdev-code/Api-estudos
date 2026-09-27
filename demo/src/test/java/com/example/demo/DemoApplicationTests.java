package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.UsuariosEntity;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		UsuariosEntity usuario = UsuariosEntity.builder()
				.id(1L)
				.nome("Teste")
				.email("teste@exemplo.com")
				.build();

		assertEquals("Teste", usuario.getNome());
		assertEquals("teste@exemplo.com", usuario.getEmail());
	}

}

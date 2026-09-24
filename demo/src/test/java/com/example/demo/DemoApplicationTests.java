package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.UsuarioEntity;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		UsuarioEntity usuario = UsuarioEntity.builder()
				.id(1L)
				.nome("Teste")
				.email("teste@exemplo.com")
				.build();

		assertEquals("Teste", usuario.getNome());
		assertEquals("teste@exemplo.com", usuario.getEmail());
	}

}

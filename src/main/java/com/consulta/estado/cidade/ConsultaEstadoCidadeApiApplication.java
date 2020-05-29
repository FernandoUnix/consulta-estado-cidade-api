package com.consulta.estado.cidade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ConsultaEstadoCidadeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaEstadoCidadeApiApplication.class, args);
	}
}

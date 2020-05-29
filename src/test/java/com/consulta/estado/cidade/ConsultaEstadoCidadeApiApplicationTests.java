package com.consulta.estado.cidade;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConsultaEstadoCidadeApiApplicationTests {

	@LocalServerPort
	private int port;
	
	public final String URL_BASE = "/api/cidades";

	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	@Test
	public void deveRetornar200ConsultaJson() {
		given().port(port).log().all().when().get(URL_BASE+"/json").then().log().all().statusCode(200).extract().response();
	}
	
	@Test
	public void deveRetornar200ConsultaCsv() {
		given().port(port).log().all().when().get(URL_BASE+"/csv").then().log().all().statusCode(200).extract().response();
	}
	
	@Test
	public void deveRetornar200ConsultaCidadeNome() {
		given().port(port).pathParam("nomeCidade", "Itapaci").log().all().when().get(URL_BASE+"/{nomeCidade}").then().log().all().statusCode(200).extract().response();
	}
	
	@Test
	public void deveRetornarCidadeNaoEncontrada() {
		
		String cidade = "Itapacizzz";
		
		given().port(port).pathParam("nomeCidade", cidade).log().all().when().get(URL_BASE+"/{nomeCidade}")
		.then().log().all().statusCode(400)
		.body("error", containsString("Não encontrado cidade com o nome: "+ cidade));
	}
}

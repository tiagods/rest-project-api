package com.tiagods.restprojectapp;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiagods.restprojectapi.service.ClientesService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestProjectAppApplicationTests {

	final String BASE_PATH = "http://localhost:9090";

	// Injetamos o repositório para acesso ao Banco de dados
	@Autowired
	private ClientesService repository;

	// Definimos o restTemplate
	private RestTemplate restTemplate;

	// Definimos o JacksonMapper responsável por converter
	// JSON para Objeto e vice versa
	private ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() throws Exception {

		// Inicializamos o objeto restTemplate
		restTemplate = new RestTemplate();
	}
}

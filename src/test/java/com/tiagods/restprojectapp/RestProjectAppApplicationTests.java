package com.tiagods.restprojectapp;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import org.json.*;
import com.tiagods.restprojectapi.model.Cliente;
import com.tiagods.restprojectapi.model.Telefone;
import com.tiagods.restprojectapi.service.ClientesService;

@RunWith(SpringJUnit4ClassRunner.class)
public class RestProjectAppApplicationTests {

	final String BASE_PATH = "http://localhost:9090/clientes";

	@Mock
	private ClientesService repository;

	private RestTemplate restTemplate = new RestTemplate();
	@Test
    public void testCreate() {
        Cliente cliente = new Cliente("Leandro", "leandro@com.br");
        cliente.getTelefones().add(new Telefone("99991111"));
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(BASE_PATH,cliente,HttpStatus.class);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
	@Test
	public void testFindOne() throws IOException{
		ResponseEntity<Cliente> cliente = restTemplate.getForEntity(BASE_PATH + "/" +
	        1, Cliente.class);
	    Assert.assertEquals("Leandro", cliente.getBody().getNome());
	}

	@Test
    public void testCreatePhone(){
		Telefone tel = new Telefone("99119090");
		ResponseEntity<HttpStatus> response = restTemplate.postForEntity(BASE_PATH + "/1/telefone",
				tel, HttpStatus.class);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
		
}
package com.tiagods.restprojectapi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiagods.restprojectapi.exception.ClienteNaoEnviadoException;
import com.tiagods.restprojectapi.exception.ClienteNotFoundException;
import com.tiagods.restprojectapi.jms.JmsMessageListener;
import com.tiagods.restprojectapi.model.Cliente;
import com.tiagods.restprojectapi.model.Telefone;
import com.tiagods.restprojectapi.repository.Clientes;

@Service
public class ClientesService {

	@Autowired 
	private Clientes clientes;
	
	@Autowired
	private JmsMessageListener jms;
	
	public Cliente buscar(Long id) {
		Optional<Cliente> cliente = clientes.findById(id);
		if(cliente.isPresent()) return cliente.get();
		else throw new ClienteNotFoundException("Cliente não existe na base");
	}
	
	public List<Cliente> listar() {
		return clientes.findAll();
	}
	public HttpStatus salvar(Cliente cliente) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String value = mapper.writeValueAsString(cliente);
			jms.putQueue(value);
			return HttpStatus.CREATED;
		}catch (JmsException e) {
			throw new ClienteNaoEnviadoException("Erro no JMS, não foi possivel enviar mensagem para servidor");
		} catch (JsonProcessingException e) {
			throw new ClienteNaoEnviadoException("Erro no cadastro, falha ao converter objeto em json");
		}
	}
	public void salvar(String cli) {
		ObjectMapper mapper = new ObjectMapper();
		Cliente cliente;
		try {
			cliente = mapper.readValue(cli, Cliente.class);
			cliente.getTelefones().forEach(c->c.setCliente(cliente));
			clientes.save(cliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		clientes.deleteAll();
	}
}

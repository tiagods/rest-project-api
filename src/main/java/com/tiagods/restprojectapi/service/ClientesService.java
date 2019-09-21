package com.tiagods.restprojectapi.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Service;

import com.tiagods.restprojectapi.exception.ClienteNaoEnviadoException;
import com.tiagods.restprojectapi.jms.JmsMessageListener;
import com.tiagods.restprojectapi.model.Cliente;
import com.tiagods.restprojectapi.repository.Clientes;

@Service
public class ClientesService {

	@Autowired 
	private Clientes clientes;
	
	@Autowired
	private JmsMessageListener jms;
	
	public List<Cliente> listar() {
		return clientes.findAll();
	}
	public HttpStatus salvar(Cliente cliente) {
		JSONObject json = new JSONObject(cliente);
		try {
			jms.putQueue(json.toString());
			return HttpStatus.CREATED;
		}catch (JmsException e) {
			throw new ClienteNaoEnviadoException("Erro no JMS, não foi possivel enviar mensagem para servidor");
		}
	}
	public void salvar(String cli) {
		JSONObject json = new JSONObject(cli);
		Cliente cliente = new Cliente();
		cliente.setNome(json.getString("nome"));
		cliente.setEmail(json.getString("email"));
		cliente.setTelefone(json.getString("telefone"));
		clientes.save(cliente);
	}
}

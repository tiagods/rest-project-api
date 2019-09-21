package com.tiagods.restprojectapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Service;

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
		if(json.has("id")) cliente.setId(json.getLong("id"));
		cliente.setNome(json.getString("nome"));
		cliente.setEmail(json.getString("email"));
		
		List<Telefone> telefones = new ArrayList<>();
		
		JSONArray array = json.getJSONArray("telefones");
		for(int i = 0; i<array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			Telefone tel = new Telefone(cliente,obj.getString("numero"));
			if(json.has("id")) tel.setId(obj.getLong("id"));
			telefones.add(tel);
		}
		cliente.setTelefones(telefones);
		clientes.save(cliente);
	}

	public void deleteAll() {
		clientes.deleteAll();
	}
}

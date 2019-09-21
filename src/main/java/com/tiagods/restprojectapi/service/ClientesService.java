package com.tiagods.restprojectapi.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void salvar(Cliente cliente) {
		JSONObject json = new JSONObject(cliente);
		jms.putQueue(json.toString());
	}
	public Cliente salvar(String cliente) {
		JSONObject json = new JSONObject(cliente);
		
		//return cliente;
		//return clientes.save(cliente);
	}
	public void setMsgQueue(String str) {
		
		
	}

}

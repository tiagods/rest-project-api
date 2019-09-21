package com.tiagods.restprojectapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagods.restprojectapi.model.Cliente;
import com.tiagods.restprojectapi.repository.Clientes;

@Service
public class ClientesService {

	@Autowired 
	private Clientes clientes;
	
	public List<Cliente> listar() {
		return clientes.findAll();
	}

	public Cliente salvar(Cliente cliente) {
		return clientes.save(cliente);
	}

}

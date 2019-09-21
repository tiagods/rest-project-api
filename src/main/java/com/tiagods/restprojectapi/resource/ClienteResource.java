package com.tiagods.restprojectapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagods.restprojectapi.model.Cliente;
import com.tiagods.restprojectapi.service.ClientesService;

@RestController
@RequestMapping("clientes")
public class ClienteResource {
	
	@Autowired
	private ClientesService clientes;
	
	 @GetMapping
	 public ResponseEntity<List<Cliente>> findAll() {
		 return new ResponseEntity<>(clientes.listar(), HttpStatus.OK);
	 }
	 @PostMapping
	 public ResponseEntity<Void> post(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clientes.salvar(cliente));
	 }
	
}

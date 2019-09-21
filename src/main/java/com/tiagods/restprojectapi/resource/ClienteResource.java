package com.tiagods.restprojectapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagods.restprojectapi.model.Cliente;
import com.tiagods.restprojectapi.model.Telefone;
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
	 @GetMapping(value = "/{id}")
	 public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		 return new ResponseEntity<>(clientes.buscar(id),HttpStatus.OK);
	 }
	 @PostMapping
	 public ResponseEntity<Void> post(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clientes.salvar(cliente));
	 }
	
	 @PutMapping(value = "/{id}")
	 public ResponseEntity<Void> atualizar(@RequestBody Cliente cliente, @PathVariable("id") Long id) {
		 Cliente cli = clientes.buscar(id);
		 cliente.setId(id);
		 cli = cliente;
		 return new ResponseEntity<>(clientes.salvar(cli));
	 }
	 @PutMapping(value = "/telefone/{id}")
	 public ResponseEntity<Void> adicionarContato(@RequestBody Telefone telefone, @PathVariable("id") Long id) {
		 Cliente cli = clientes.buscar(id);
		 cli.setId(id);
		 cli.getTelefones().add(telefone);
		 return new ResponseEntity<>(clientes.salvar(cli));
	 }
}

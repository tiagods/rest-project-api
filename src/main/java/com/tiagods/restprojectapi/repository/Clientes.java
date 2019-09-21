package com.tiagods.restprojectapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagods.restprojectapi.model.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>{

}

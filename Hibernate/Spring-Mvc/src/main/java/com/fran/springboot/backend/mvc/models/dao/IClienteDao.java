package com.fran.springboot.backend.mvc.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.fran.springboot.backend.mvc.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}

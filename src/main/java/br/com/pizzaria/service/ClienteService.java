package br.com.pizzaria.service;

import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRep;

    @Transactional(rollbackFor = Exception.class)
    public void create(Cliente cliente){


        clienteRep.save(cliente);


    }


}

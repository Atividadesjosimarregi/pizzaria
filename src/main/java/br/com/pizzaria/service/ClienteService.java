package br.com.pizzaria.service;

import br.com.pizzaria.dto.ClienteDTO;
import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarCliente(final ClienteDTO cliente){

        var clientes = new Cliente();
        BeanUtils.copyProperties(cliente,clientes);

        Assert.isTrue(clientes.getNome().length() <=50,"Nome só pode ter até 50 caracteres");
        Assert.isTrue(clientes.getNome() != null,"Nome não pode ser nulo");




        this.clienteRep.save(clientes);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaCliente(ClienteDTO clienteDTO) {

        Cliente clienteExistente = this.clienteRep.findById(clienteDTO.getId()).orElse(null);


        if (clienteExistente != null) {

            BeanUtils.copyProperties(clienteDTO, clienteExistente);

            Assert.isTrue(clienteExistente.getNome().length() <=50,"Nome só pode ter até 50 caracteres");
            Assert.isTrue(clienteExistente.getNome() != null,"Nome não pode ser nulo");


            this.clienteRep.save(clienteExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirCliente(final Long id){

        final Cliente clienteBanco = this.clienteRep.findById(id).orElse(null);

        if (clienteBanco == null || clienteBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o cliente informado.");
        }
        this.clienteRep.delete(clienteBanco);
    }


}

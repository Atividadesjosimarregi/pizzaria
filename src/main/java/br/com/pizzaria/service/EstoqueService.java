package br.com.pizzaria.service;


import br.com.pizzaria.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRep;
}

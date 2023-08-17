package br.com.pizzaria.service;

import br.com.pizzaria.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaborService {
    @Autowired
    private SaborRepository saborRep;
}

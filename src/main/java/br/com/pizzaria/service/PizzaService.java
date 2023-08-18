package br.com.pizzaria.service;

import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.dto.PizzaDTO;
import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.entity.Pizza;
import br.com.pizzaria.repository.EnderecoRepository;
import br.com.pizzaria.repository.PizzaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRep;



    @Transactional(rollbackFor = Exception.class)
    public void cadastrarPizza(final PizzaDTO pizza){

        var pizzas = new Pizza();
        BeanUtils.copyProperties(pizza,pizzas);




        this.pizzaRep.save(pizzas);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizPizza(PizzaDTO pizzaDTO) {

        Pizza pizzaExistente = this.pizzaRep.findById(pizzaDTO.getId()).orElse(null);


        if (pizzaExistente != null) {

            BeanUtils.copyProperties(pizzaDTO, pizzaExistente);


            this.pizzaRep.save(pizzaExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirPizza(final Long id){

        final Pizza pizzaBanco = this.pizzaRep.findById(id).orElse(null);

        if (pizzaBanco == null || pizzaBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o pizza informado.");
        }
        this.pizzaRep.delete(pizzaBanco);
    }
}

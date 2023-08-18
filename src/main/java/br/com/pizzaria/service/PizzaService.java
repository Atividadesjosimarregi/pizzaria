package br.com.pizzaria.service;

import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.dto.PizzaDTO;
import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.entity.Pizza;
import br.com.pizzaria.entity.Tamanho;
import br.com.pizzaria.repository.EnderecoRepository;
import br.com.pizzaria.repository.PizzaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRep;



    @Transactional(rollbackFor = Exception.class)
    public void cadastrarPizza(final PizzaDTO pizza){

        var pizzas = new Pizza();
        BeanUtils.copyProperties(pizza,pizzas);

        Assert.isTrue(pizzas.getSabores() != null,"A pizza deve conter pelo menos 1 sabor");
        Assert.isTrue(pizzas.getQuantidade() != 0,"Adicione a quantidade de pizzas");

        if(pizzas.getTamanho() == Tamanho.P){

            Assert.isTrue(pizzas.getSabores().size() == 1,"Pizza pequena só tem até 1 sabor");
            pizzas.setPreco(20);

        }

        if(pizzas.getTamanho() == Tamanho.M){

            Assert.isTrue(pizzas.getSabores().size() >= 1 || pizzas.getSabores().size() <= 2,"Pizza Média só tem de 1 a 2 sabores");
            pizzas.setPreco(30);
        }

        if(pizzas.getTamanho() == Tamanho.G){

            Assert.isTrue(pizzas.getSabores().size() >= 1 || pizzas.getSabores().size() <= 3,"Pizza Grande só tem de 1 a 3 sabores");
            pizzas.setPreco(40);
        }

        if(pizzas.getTamanho() == Tamanho.GG){

            Assert.isTrue(pizzas.getSabores().size() >= 1 || pizzas.getSabores().size() <= 4,"Pizza Gigante só tem de 1 a 4 sabores");
            pizzas.setPreco(50);
        }


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

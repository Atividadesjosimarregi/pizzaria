package br.com.pizzaria.service;


import br.com.pizzaria.dto.PizzaDTO;
import br.com.pizzaria.entity.Pizza;
import br.com.pizzaria.entity.Tamanho;
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
    public PizzaDTO cadastrarPizza(final PizzaDTO pizza){

        var pizza1 = new Pizza();
        BeanUtils.copyProperties(pizza, pizza1);

        if(pizza.getTamanho() == Tamanho.P){
            Assert.isTrue(pizza.getSabores().size() == 1, "Pizzas do tamanho P não podem conter mais de um sabor");
            pizza.setPreco(15);
        }else if (pizza.getTamanho() == Tamanho.M) {
            Assert.isTrue(pizza.getSabores().size() >= 1 && pizza.getSabores().size() <=2, "Pizzas do tamanho M não podem conter mais de 02 sabores");
            pizza.setPreco(25);
        }else if (pizza.getTamanho() == Tamanho.G)
        {
            Assert.isTrue(pizza.getSabores().size() >= 1 && pizza.getSabores().size() <=3, "Pizzas do tamanho G não podem conter mais de 03 sabores");
            pizza.setPreco(30);
        }else {
            Assert.isTrue(pizza.getSabores().size() >= 1 && pizza.getSabores().size() <=4, "Pizzas do tamanho GG não podem conter mais de 04 sabores");
            pizza.setPreco(45);
        }

        Assert.isTrue(pizza.getQuantidade() != 0, "Quantidade não pode ser nula");

        float total;

        total = pizza.getPreco() * pizza.getQuantidade();
        pizza.setPreco(total);

        Pizza pizzaEntity = new Pizza();
        BeanUtils.copyProperties(pizza, pizzaEntity);
        Pizza pizzaSalva = this.pizzaRep.save(pizzaEntity);

        PizzaDTO pizzaDTO2 = new PizzaDTO();
        BeanUtils.copyProperties(pizzaSalva,pizzaDTO2);
        return pizzaDTO2;

    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizPizza(PizzaDTO pizzaDTO) {

        Pizza pizzaExistente = this.pizzaRep.findById(pizzaDTO.getId()).orElse(null);


        if (pizzaExistente != null) {

            BeanUtils.copyProperties(pizzaDTO, pizzaExistente);

            Assert.isTrue(pizzaExistente.getSabores() != null,"A pizza deve conter pelo menos 1 sabor");
            Assert.isTrue(pizzaExistente.getQuantidade() != 0,"Adicione a quantidade de pizzas");
            Assert.isTrue(pizzaExistente.getTamanho() != null,"Tamanho não pode ser nulo");


            this.pizzaRep.save(pizzaExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirPizza(final Long id){

        final Pizza pizzaBanco = this.pizzaRep.findById(id).orElse(null);

        if (pizzaBanco == null || pizzaBanco.getId()!=(id)){
            Assert.isTrue(2 == 3,"Não foi possivel identificar o registro informado");
        }
        this.pizzaRep.delete(pizzaBanco);
    }
}

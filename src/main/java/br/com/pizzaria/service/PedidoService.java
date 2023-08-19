package br.com.pizzaria.service;

import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.dto.PedidoDTO;
import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.entity.Pedido;
import br.com.pizzaria.entity.Pizza;
import br.com.pizzaria.entity.Sabor;
import br.com.pizzaria.repository.EnderecoRepository;
import br.com.pizzaria.repository.PedidoRepository;
import br.com.pizzaria.repository.PizzaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRep;

    @Autowired
    private PizzaRepository pizzaRep;



    @Transactional(rollbackFor = Exception.class)
    public void cadastrarPedido(final PedidoDTO pedido){

        Pizza pizza = this.pizzaRep.findById(1L).orElse(null);

        BigDecimal total = BigDecimal.valueOf(0);
        var pedidos = new Pedido();
        BeanUtils.copyProperties(pedido,pedidos);


        Assert.isTrue(pedidos.getCliente() != null,"Cliente não pode ser nulo");
        Assert.isTrue(pedidos.getFuncionario() != null, "Funcionário não pode ser nulo");
        Assert.isTrue(pedidos.getStatus() != null,"Status não pode ser nulo");


        pedido.setPreco(pedido.getPizzas().get(0).getPreco());


        /*if(pedido.getPizzas().size() >= 1){
            for(int i=0;i<pedidos.getPizzas().size(); i++) {
                total += pedidos.getPizzas().get(i).getPreco();
                System.out.println(total);
            }
        }*/

        //BigDecimal ValorPizzas = pedidos.getPizzas().stream().map(Pizza::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);
        //pedidos.setPreco(ValorPizzas);

        gerarArquivoPedido(pedidos);
        this.pedidoRep.save(pedidos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaPedido(PedidoDTO pedidoDTO, Long id) {

        Pedido pedidoExistente = this.pedidoRep.findById(id).orElse(null);

        float total = 0;
        var pedidos = new Pedido();
        BeanUtils.copyProperties(pedidoDTO, pedidoExistente);



        Assert.isTrue(pedidoExistente.getCliente() != null,"Cliente não pode ser nulo");
        Assert.isTrue(pedidoExistente.getFuncionario() != null, "Funcionário não pode ser nulo");
        Assert.isTrue(pedidoExistente.getStatus() != null,"Status não pode ser nulo");

        BigDecimal ValorPizzas = pedidoExistente.getPizzas().stream().map(Pizza::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);

        pedidoExistente.setPreco(ValorPizzas);

        System.out.println(ValorPizzas);


        this.pedidoRep.save(pedidoExistente);

    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirPedido(final Long id){

        final Pedido pedidoBanco = this.pedidoRep.findById(id).orElse(null);

        if (pedidoBanco == null || pedidoBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o pedido informado.");
        }
        this.pedidoRep.delete(pedidoBanco);
    }

    private void gerarArquivoPedido(Pedido pedido) {
        try (FileWriter writer = new FileWriter("pedido_" + pedido.getId() + ".txt")) {
            writer.write("Detalhes do Pedido:\n");
            writer.write("ID do Pedido: " + pedido.getId() + "\n");

            List<Pizza> pizzas = pedido.getPizzas();
            for (int i = 0; i < pizzas.size(); i++) {
                Pizza pizza = pizzas.get(i);
                writer.write("Pizza " + (i + 1) + ":\n");

                List<Sabor> sabores = pizza.getSabores();
                if (sabores != null) {
                    writer.write("Sabores: ");
                    for (int j = 0; j < sabores.size(); j++) {
                        writer.write(sabores.get(j).getSabor());
                        if (j < sabores.size() - 1) {
                            writer.write(", ");
                        }
                    }
                    writer.write("\n");
                }

                // Adicione mais detalhes da pizza aqui, como tamanho, preço, etc.
                writer.write("\n");
            }

            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    }

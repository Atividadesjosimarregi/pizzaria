package br.com.pizzaria.service;

import br.com.pizzaria.dto.PedidoDTO;
import br.com.pizzaria.entity.*;
import br.com.pizzaria.repository.PedidoRepository;
import br.com.pizzaria.repository.PizzaRepository;
import br.com.pizzaria.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRep;

    @Autowired
    private PizzaRepository pizzaRep;

    @Autowired
    private ProdutoRepository produtoRep;



    @Transactional(rollbackFor = Exception.class)
    public void cadastrarPedido(final PedidoDTO pedido){



        float totalPizzas = 0;
        float totalProdutos = 0;
        var pedidos = new Pedido();
        BeanUtils.copyProperties(pedido,pedidos);

        if(pedidos.getEstoque()!=null){
                for(Estoque estoque : pedidos.getEstoque()) {
                    Optional<Produto> produtoTempo = produtoRep.findById(estoque.getId());
                    totalProdutos += produtoTempo.get().getPrecoProduto();
                }
        }


        if(pedidos.getPizzas().size() >= 1){
            for(Pizza pizza : pedidos.getPizzas()) {
                Optional<Pizza> pizzaTempo = pizzaRep.findById(pizza.getId());
                totalPizzas += pizzaTempo.get().getPreco();

            }
        }
        pedidos.setPreco(totalPizzas+totalProdutos);

        gerarArquivoPedido(pedidos);
        this.pedidoRep.save(pedidos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaPedido(PedidoDTO pedidoDTO, Long id) {

        Pedido pedidoExistente = this.pedidoRep.findById(id).orElse(null);


        BeanUtils.copyProperties(pedidoDTO, pedidoExistente);

        Assert.isTrue(pedidoExistente.getCliente() != null,"Cliente não pode ser nulo");
        Assert.isTrue(pedidoExistente.getFuncionario() != null, "Funcionário não pode ser nulo");
        Assert.isTrue(pedidoExistente.getStatus() != null,"Status não pode ser nulo");


        this.pedidoRep.save(pedidoExistente);

    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirPedido(final Long id){

        final Pedido pedidoBanco = this.pedidoRep.findById(id).orElse(null);

        if (pedidoBanco == null || pedidoBanco.getId()!=(id)){
            Assert.isTrue(2 == 3 , "Não foi possivel identificar o registro informado");
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
                        writer.write(sabores.get(j).getSaborr());
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

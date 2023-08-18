package br.com.pizzaria.service;

import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.dto.PedidoDTO;
import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.entity.Pedido;
import br.com.pizzaria.entity.Pizza;
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

        float total = 0;
        var pedidos = new Pedido();
        BeanUtils.copyProperties(pedido,pedidos);


        Assert.isTrue(pedidos.getCliente() != null,"Cliente não pode ser nulo");
        Assert.isTrue(pedidos.getFuncionario() != null, "Funcionário não pode ser nulo");
        Assert.isTrue(pedidos.getStatus() != null,"Status não pode ser nulo");





        if(pedidos.getPizzas().size() >= 1){
            for(int i=0;i<pedidos.getPizzas().size(); i++) {
                total += pedidos.getPizzas().get(i).getPreco();
                System.out.println(total);
            }
        }

        System.out.println(pizza.getPreco());
        System.out.println(pedidos.getPizzas().get(0).getPreco());


        total = pedido.getPizzas().get(0).getPreco();

        pedidos.setPreco(total);


        this.pedidoRep.save(pedidos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaPedido(PedidoDTO pedidoDTO, Long id) {

        Pedido pedidoExistente = this.pedidoRep.findById(id).orElse(null);

        float total = 0;
        var pedidos = new Pedido();
        BeanUtils.copyProperties(pedidoDTO, pedidoExistente);

        if(pedidoExistente.getPizzas().size() >= 1){
            for(int i=0;i<pedidoExistente.getPizzas().size(); i++) {
                total += pedidoExistente.getPizzas().get(i).getPreco();
                System.out.println(total);
            }
        }

        pedidoExistente.setPreco(223);

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

    public void gerarRelatorioCozinha() {
        List<Pedido> pedidos = pedidoRep.findAll();

        try (FileWriter writer = new FileWriter("relatorio_cozinha.txt")) {
            for (Pedido pedido : pedidos) {
                writer.write("ID do Pedido: " + pedido.getId() + "\n");
                // Adicione mais detalhes do pedido aqui
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

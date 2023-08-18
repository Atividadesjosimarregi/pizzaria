package br.com.pizzaria.service;

import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.dto.PedidoDTO;
import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.entity.Pedido;
import br.com.pizzaria.repository.EnderecoRepository;
import br.com.pizzaria.repository.PedidoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRep;



    @Transactional(rollbackFor = Exception.class)
    public void cadastrarPedido(final PedidoDTO pedido){

        float total = 0;
        var pedidos = new Pedido();
        BeanUtils.copyProperties(pedido,pedidos);

        if(pedidos.getPizzas().size() >= 1){
            for(int i=0;i<pedidos.getPizzas().size(); i++) {
                total += pedidos.getPizzas().get(i).getPreco();
            }
        }

        pedidos.setPreco(total);


        this.pedidoRep.save(pedidos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaPedido(PedidoDTO pedidoDTO) {

        Pedido pedidoExistente = this.pedidoRep.findById(pedidoDTO.getId()).orElse(null);


        if (pedidoExistente != null) {

            BeanUtils.copyProperties(pedidoDTO, pedidoExistente);


            this.pedidoRep.save(pedidoExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirPedido(final Long id){

        final Pedido pedidoBanco = this.pedidoRep.findById(id).orElse(null);

        if (pedidoBanco == null || pedidoBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o pedido informado.");
        }
        this.pedidoRep.delete(pedidoBanco);
    }

}

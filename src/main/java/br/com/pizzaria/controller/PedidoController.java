package br.com.pizzaria.controller;

import br.com.pizzaria.dto.PedidoDTO;
import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.entity.Pedido;
import br.com.pizzaria.entity.Status;
import br.com.pizzaria.repository.ClienteRepository;
import br.com.pizzaria.repository.PedidoRepository;
import br.com.pizzaria.service.ClienteService;
import br.com.pizzaria.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.pizzaria.entity.Status.ENTREGUE;
import static br.com.pizzaria.entity.Status.ANDAMENTO;


@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRep;

    @Autowired
    private PedidoService pedidoServ;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable("id") final Long id) {
        final Pedido pedido = this.pedidoRep.findById(id).orElse(null);
        return ResponseEntity.ok(pedido);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Pedido>> List(){
        return ResponseEntity.ok(this.pedidoRep.findAll());

    }

    @GetMapping("/solicitados")
    public ResponseEntity <List<Pedido>> solicitados(){

        return ResponseEntity.ok(this.pedidoRep.findByStatus(ANDAMENTO));
    }



    @GetMapping("/pedidosDoDia")
    public List<Pedido> getPedidosDoDia() {
        LocalDate dataAtual = LocalDate.now();

        LocalDateTime inicioDoDia = dataAtual.atStartOfDay();
        LocalDateTime fimDoDia = dataAtual.atTime(23, 59, 59);

        List<Pedido> pedidosDoDia = pedidoRep.findByCadastroBetween(inicioDoDia, fimDoDia);

        return pedidosDoDia;
    }

    @GetMapping("/pedidosEncerradosDoDia")
    public List<Pedido> getPedidosEncerradosDoDia() {
        LocalDate dataAtual = LocalDate.now();

        LocalDateTime inicioDoDia = dataAtual.atStartOfDay();
        LocalDateTime fimDoDia = dataAtual.atTime(23, 59, 59);

        List<Pedido> pedidosEncerradosDoDia = pedidoRep.findByStatusAndCadastroBetween(
                ENTREGUE, inicioDoDia, fimDoDia
        );

        return pedidosEncerradosDoDia;
    }

    @GetMapping("/pedidosCanceladosDoDia")
    public List<Pedido> getPedidosCanceladosDoDia() {
        LocalDate dataAtual = LocalDate.now();

        List<Pedido> pedidosCanceladosDoDia = pedidoRep.findByCanceladoAndCadastroBetween(true, dataAtual.atStartOfDay(), dataAtual.atTime(23, 59, 59));

        return pedidosCanceladosDoDia;
    }


    @GetMapping("/delivery/{ativo}")
    public ResponseEntity<?> delivery(@PathVariable("ativo") boolean delivery) {
        List<Pedido> pedidos;
        if (!delivery) {
            pedidos = pedidoRep.findByDelivery(false);
        } else {
            pedidos = pedidoRep.findByDelivery(true);
        }

        long entregasPorDelivery = pedidos.stream()
                .filter(pedido -> pedido.isEntrega())
                .count();

        long entregasPorBalcao = pedidos.size() - entregasPorDelivery;

        Map<String, Long> resultado = new HashMap<>();
        resultado.put("entregasPorDelivery", entregasPorDelivery);
        resultado.put("entregasPorBalcao", entregasPorBalcao);

        return ResponseEntity.ok(resultado);
    }




    @PostMapping
    public ResponseEntity <?> cadastra(@RequestBody final PedidoDTO pedido){
        try {
            this.pedidoServ.cadastrarPedido(pedido);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edita(@PathVariable("id") final Long id, @RequestBody final PedidoDTO pedido){
        try {
            final Pedido pedido1 = this.pedidoRep.findById(id).orElse(null);

            if (pedido1 == null || pedido1.getId() != (pedido.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.pedidoServ.atualizaPedido(pedido,id);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        try {

            this.pedidoServ.excluirPedido(id);
            return ResponseEntity.ok("excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}

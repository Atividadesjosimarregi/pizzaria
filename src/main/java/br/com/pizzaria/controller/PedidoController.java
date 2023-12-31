package br.com.pizzaria.controller;

import br.com.pizzaria.dto.PedidoDTO;
import br.com.pizzaria.entity.Pedido;
import br.com.pizzaria.entity.Status;
import br.com.pizzaria.repository.PedidoRepository;
import br.com.pizzaria.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<List<Pedido>> list(){
        return ResponseEntity.ok(this.pedidoRep.findAll());

    }

    @GetMapping("/solicitados")
    public ResponseEntity<List<Pedido>> solicitados() {
        List<Pedido> pedidosAndamento = this.pedidoRep.findByStatus(Status.ANDAMENTO);
        return ResponseEntity.ok(pedidosAndamento);
    }


    @GetMapping("/pedidosDoDia")
    public List<Pedido> getPedidosDoDia() {
        LocalDate dataAtual = LocalDate.now();

        LocalDateTime inicioDoDia = dataAtual.atStartOfDay();
        LocalDateTime fimDoDia = dataAtual.atTime(23, 59, 59);

        return pedidoRep.findByCadastroBetween(inicioDoDia, fimDoDia);
    }

    @GetMapping("/pedidosEncerradosDoDia")
    public List<Pedido> getPedidosEncerradosDoDia() {
        LocalDate dataAtual = LocalDate.now();

        LocalDateTime inicioDoDia = dataAtual.atStartOfDay();
        LocalDateTime fimDoDia = dataAtual.atTime(23, 59, 59);

        return pedidoRep.findByStatusAndCadastroBetween(
                ENTREGUE, inicioDoDia, fimDoDia
        );
    }

    @GetMapping("/pedidosCanceladosDoDia")
    public List<Pedido> getPedidosCanceladosDoDia() {
        LocalDate dataAtual = LocalDate.now();

        return pedidoRep.findByCanceladoAndCadastroBetween(true, dataAtual.atStartOfDay(), dataAtual.atTime(23, 59, 59));
    }


    @GetMapping("/delivery/{ativo}")
    public ResponseEntity<Map<String, Long>> delivery(@PathVariable("ativo") boolean delivery) {
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
    public ResponseEntity <String> cadastra(@RequestBody final PedidoDTO pedido){
        try {
            this.pedidoServ.cadastrarPedido(pedido);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> edita(@PathVariable("id") final Long id, @RequestBody final PedidoDTO pedido){
        try {
            final Pedido pedido1 = this.pedidoRep.findById(id).orElse(null);

            if (pedido1 == null || pedido1.getId() != (pedido.getId())){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            this.pedidoServ.atualizaPedido(pedido,id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.pedidoServ.excluirPedido(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}

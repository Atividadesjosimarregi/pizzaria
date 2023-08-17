package br.com.pizzaria.controller;

import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.entity.Pedido;
import br.com.pizzaria.repository.ClienteRepository;
import br.com.pizzaria.repository.PedidoRepository;
import br.com.pizzaria.service.ClienteService;
import br.com.pizzaria.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRep;

    @Autowired
    private PedidoService pedidoServ;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Pedido pedido = this.pedidoRep.findById(id).orElse(null);
        return ResponseEntity.ok(pedido);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> List(){
        return ResponseEntity.ok(this.pedidoRep.findAll());

    }


    @PostMapping
    public ResponseEntity <?> cadastra(@RequestBody final Pedido pedido){
        try {
            this.pedidoRep.save(pedido);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edita(@PathVariable("id") final Long id, @RequestBody final Pedido pedido){
        try {
            final Pedido pedido1 = this.pedidoRep.findById(id).orElse(null);

            if (pedido1 == null || pedido1.getId() != (pedido.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.pedidoRep.save(pedido);
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

            this.pedidoRep.deleteById(id);
            return ResponseEntity.ok("Desativado ou excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}

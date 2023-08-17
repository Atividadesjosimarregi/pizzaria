package br.com.pizzaria.controller;

import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.entity.Estoque;
import br.com.pizzaria.repository.ClienteRepository;
import br.com.pizzaria.repository.EstoqueRepository;
import br.com.pizzaria.service.ClienteService;
import br.com.pizzaria.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRep;

    @Autowired
    private EstoqueService estoqueServ;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Estoque estoque = this.estoqueRep.findById(id).orElse(null);
        return ResponseEntity.ok(estoque);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> List(){
        return ResponseEntity.ok(this.estoqueRep.findAll());

    }

    @PostMapping
    public ResponseEntity <?> cadastra(@RequestBody final Estoque estoque){
        try {
            this.estoqueRep.save(estoque);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edita(@PathVariable("id") final Long id, @RequestBody final Estoque estoque){
        try {
            final Estoque estoque1 = this.estoqueRep.findById(id).orElse(null);

            if (estoque1 == null || estoque1.getId() != (estoque.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.estoqueRep.save(estoque);
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

            this.estoqueRep.deleteById(id);
            return ResponseEntity.ok("Desativado ou excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }



}

package br.com.pizzaria.controller;

import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.entity.Pizza;
import br.com.pizzaria.repository.ClienteRepository;
import br.com.pizzaria.repository.PizzaRepository;
import br.com.pizzaria.service.ClienteService;
import br.com.pizzaria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pizza")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRep;

    @Autowired
    private PizzaService pizzaServ;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Pizza pizza = this.pizzaRep.findById(id).orElse(null);
        return ResponseEntity.ok(pizza);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> List(){
        return ResponseEntity.ok(this.pizzaRep.findAll());

    }


    @PostMapping
    public ResponseEntity <?> cadastra(@RequestBody final Pizza pizza){
        try {
            this.pizzaRep.save(pizza);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edita(@PathVariable("id") final Long id, @RequestBody final Pizza pizza){
        try {
            final Pizza pizza1 = this.pizzaRep.findById(id).orElse(null);

            if (pizza1 == null || pizza1.getId() != (pizza.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.pizzaRep.save(pizza);
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

            this.pizzaRep.deleteById(id);
            return ResponseEntity.ok("Desativado ou excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}

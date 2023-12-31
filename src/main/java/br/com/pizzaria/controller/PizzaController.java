package br.com.pizzaria.controller;

import br.com.pizzaria.dto.PizzaDTO;
import br.com.pizzaria.entity.Pizza;
import br.com.pizzaria.repository.PizzaRepository;
import br.com.pizzaria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pizza")
@CrossOrigin(origins = "http://localhost:4200")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRep;

    @Autowired
    private PizzaService pizzaServ;

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> findById(@PathVariable("id") final Long id) {
        final Pizza pizza = this.pizzaRep.findById(id).orElse(null);
        return ResponseEntity.ok(pizza);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Pizza>> list(){
        return ResponseEntity.ok(this.pizzaRep.findAll());

    }


    @PostMapping
    public PizzaDTO cadastrarPizza(@Validated @RequestBody final PizzaDTO pizza) {
        try {
            return this.pizzaServ.cadastrarPizza(pizza);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return null;
        }
    }

    @PutMapping
    public ResponseEntity<String> edita(@PathVariable("id") final Long id, @RequestBody final PizzaDTO pizza){
        try {
            final Pizza pizza1 = this.pizzaRep.findById(id).orElse(null);

            if (pizza1 == null || pizza1.getId() != (pizza.getId())){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            this.pizzaServ.atualizPizza(pizza);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.pizzaServ.excluirPizza(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }


}

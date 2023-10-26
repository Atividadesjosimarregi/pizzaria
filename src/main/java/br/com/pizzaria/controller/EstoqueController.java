package br.com.pizzaria.controller;

import br.com.pizzaria.dto.EstoqueDTO;
import br.com.pizzaria.dto.SaborDTO;
import br.com.pizzaria.entity.Estoque;
import br.com.pizzaria.repository.EstoqueRepository;
import br.com.pizzaria.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estoque")
@CrossOrigin(origins = "http://localhost:4200")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRep;

    @Autowired
    private EstoqueService estoqueServ;

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> findById(@PathVariable("id") final Long id) {
        final Estoque estoque = this.estoqueRep.findById(id).orElse(null);
        return ResponseEntity.ok(estoque);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Estoque>> list(){
        return ResponseEntity.ok(this.estoqueRep.findAll());

    }

    @PostMapping
    public ResponseEntity<HttpStatus> cadastrar(@Validated @RequestBody final EstoqueDTO estoque) {
        try {
            this.estoqueServ.cadastrarEstoque(estoque);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> edita(@RequestBody final EstoqueDTO estoqueDTO){
        try {
            this.estoqueServ.atualizaEstoque(estoqueDTO);

            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (RuntimeException e){

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.estoqueServ.excluirEstoque(id);
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

package br.com.pizzaria.controller;

import br.com.pizzaria.dto.FuncionarioDTO;
import br.com.pizzaria.dto.SaborDTO;
import br.com.pizzaria.entity.Sabor;
import br.com.pizzaria.repository.SaborRepository;
import br.com.pizzaria.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sabor")
@CrossOrigin(origins = "http://localhost:4200")
public class SaborController {

    @Autowired
    private SaborRepository saborRep;

    @Autowired
    private SaborService saborServ;

    @GetMapping("/{id}")
    public ResponseEntity<Sabor> findById(@PathVariable("id") final Long id) {
        final Sabor sabor = this.saborRep.findById(id).orElse(null);
        return ResponseEntity.ok(sabor);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Sabor>> list(){
        return ResponseEntity.ok(this.saborRep.findAll());

    }

    @PostMapping
    public ResponseEntity <String> cadastra(@RequestBody final SaborDTO sabor){
        try {
            this.saborServ.cadastrarSabor(sabor);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> edita(@RequestBody final SaborDTO saborDTO){
        try {
            this.saborServ.atualizaSabor(saborDTO);

            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (RuntimeException e){

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.saborServ.excluirSabor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}

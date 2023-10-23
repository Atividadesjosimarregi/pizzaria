package br.com.pizzaria.controller;

import br.com.pizzaria.dto.FuncionarioDTO;
import br.com.pizzaria.entity.Funcionario;
import br.com.pizzaria.entity.Sabor;
import br.com.pizzaria.repository.FuncionarioRepository;
import br.com.pizzaria.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionario")
@CrossOrigin(origins = "http://localhost:4200")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRep;

    @Autowired
    private FuncionarioService funcionarioServ;

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable("id") final Long id) {
        final Funcionario funcionario = this.funcionarioRep.findById(id).orElse(null);
        return ResponseEntity.ok(funcionario);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Funcionario>> list(){
        return ResponseEntity.ok(this.funcionarioRep.findAll());

    }


    @PostMapping
    public ResponseEntity <String> cadastra(@RequestBody final FuncionarioDTO funcionario){
        try {
            this.funcionarioServ.cadastrarFuncionario(funcionario);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> edita(@RequestBody final FuncionarioDTO funcionarioDTO){
        try {
            this.funcionarioServ.atualizaFuncionario(funcionarioDTO);

            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (RuntimeException e){

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.funcionarioServ.excluirFuncionario(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



}

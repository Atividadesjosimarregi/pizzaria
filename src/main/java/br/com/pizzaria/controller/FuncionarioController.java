package br.com.pizzaria.controller;

import br.com.pizzaria.dto.FuncionarioDTO;
import br.com.pizzaria.entity.Funcionario;
import br.com.pizzaria.entity.Sabor;
import br.com.pizzaria.repository.FuncionarioRepository;
import br.com.pizzaria.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edita(@PathVariable("id") final Long id, @RequestBody final FuncionarioDTO funcionario){
        try {
            final Funcionario funcionario1 = this.funcionarioRep.findById(id).orElse(null);

            if (funcionario1 == null || funcionario1.getId() != (funcionario.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.funcionarioServ.atualizaFuncionario(funcionario);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("ERror: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.funcionarioServ.excluirFuncionario(id);
            return ResponseEntity.ok("excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("ERRor: " + e.getMessage());
        }
    }



}

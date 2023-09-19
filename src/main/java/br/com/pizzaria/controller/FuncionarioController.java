package br.com.pizzaria.controller;

import br.com.pizzaria.dto.FuncionarioDTO;
import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.entity.Funcionario;
import br.com.pizzaria.repository.ClienteRepository;
import br.com.pizzaria.repository.FuncionarioRepository;
import br.com.pizzaria.service.ClienteService;
import br.com.pizzaria.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/funcionario")
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
    public ResponseEntity<?> List(){
        return ResponseEntity.ok(this.funcionarioRep.findAll());

    }


    @PostMapping
    public ResponseEntity <?> cadastra(@RequestBody final FuncionarioDTO funcionario){
        try {
            this.funcionarioServ.cadastrarFuncionario(funcionario);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edita(@PathVariable("id") final Long id, @RequestBody final FuncionarioDTO funcionario){
        try {
            final Funcionario funcionario1 = this.funcionarioRep.findById(id).orElse(null);

            if (funcionario1 == null || funcionario1.getId() != (funcionario.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.funcionarioServ.atualizaFuncionario(funcionario);
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

            this.funcionarioServ.excluirFuncionario(id);
            return ResponseEntity.ok("excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }



}

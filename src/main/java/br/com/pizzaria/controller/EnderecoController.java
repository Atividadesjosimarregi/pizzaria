package br.com.pizzaria.controller;

import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.repository.ClienteRepository;
import br.com.pizzaria.repository.EnderecoRepository;
import br.com.pizzaria.service.ClienteService;
import br.com.pizzaria.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRep;

    @Autowired
    private EnderecoService enderecoServ;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Endereco endereco = this.enderecoRep.findById(id).orElse(null);
        return ResponseEntity.ok(endereco);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> List(){
        return ResponseEntity.ok(this.enderecoRep.findAll());

    }

    @PostMapping
    public ResponseEntity <?> cadastra(@RequestBody final Endereco endereco){
        try {
            this.enderecoRep.save(endereco);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edita(@PathVariable("id") final Long id, @RequestBody final Endereco endereco){
        try {
            final Endereco endereco1 = this.enderecoRep.findById(id).orElse(null);

            if (endereco1 == null || endereco1.getId() != (endereco.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.enderecoRep.save(endereco);
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

            this.enderecoRep.deleteById(id);
            return ResponseEntity.ok("Desativado ou excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }



}

package br.com.pizzaria.controller;

import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.repository.ClienteRepository;
import br.com.pizzaria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRep;

    @Autowired
    private ClienteService clienteServ;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Cliente cliente = this.clienteRep.findById(id).orElse(null);
        return ResponseEntity.ok(cliente);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> List(){
        return ResponseEntity.ok(this.clienteRep.findAll());

    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> Nome(@PathVariable("nome") String nome){
        Cliente cliente = clienteRep.findByNome(nome);
        return ResponseEntity.ok(cliente);

    }


    @PostMapping
    public ResponseEntity <?> cadastra(@RequestBody final Cliente cliente){
        try {
            this.clienteRep.save(cliente);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edita(@PathVariable("id") final Long id, @RequestBody final Cliente cliente){
        try {
            final Cliente cliente1 = this.clienteRep.findById(id).orElse(null);

            if (cliente1 == null || cliente1.getId() != (cliente.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.clienteRep.save(cliente);
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

            this.clienteRep.deleteById(id);
            return ResponseEntity.ok("Desativado ou excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}

package br.com.pizzaria.controller;

import br.com.pizzaria.dto.SaborDTO;
import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.entity.Sabor;
import br.com.pizzaria.repository.ClienteRepository;
import br.com.pizzaria.repository.SaborRepository;
import br.com.pizzaria.service.ClienteService;
import br.com.pizzaria.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sabor")
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
    public ResponseEntity<List<Sabor>> List(){
        return ResponseEntity.ok(this.saborRep.findAll());

    }




    @PostMapping
    public ResponseEntity <?> cadastra(@RequestBody final SaborDTO sabor){
        try {
            this.saborServ.cadastrarSabor(sabor);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edita(@PathVariable("id") final Long id, @RequestBody final SaborDTO sabor){
        try {
            final Sabor sabor1 = this.saborRep.findById(id).orElse(null);

            if (sabor1 == null || sabor1.getId() != (sabor.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.saborServ.atualizaSabor(sabor);
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

            this.saborServ.excluirSabor(id);
            return ResponseEntity.ok("excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}

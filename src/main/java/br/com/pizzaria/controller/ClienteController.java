package br.com.pizzaria.controller;

import br.com.pizzaria.dto.ClienteDTO;
import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.repository.ClienteRepository;
import br.com.pizzaria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRep;

    @Autowired
    private ClienteService clienteServ;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable("id") final Long id) {
        final Cliente cliente = this.clienteRep.findById(id).orElse(null);
        return ResponseEntity.ok(cliente);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Cliente>> list(){
        return ResponseEntity.ok(this.clienteRep.findAll());

    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Cliente>> nomeBusca(@PathVariable("nome") String nome){
        List<Cliente> cliente = clienteRep.findByNome(nome);
        return ResponseEntity.ok(cliente);

    }


    @PostMapping
    public ResponseEntity <String> cadastra(@RequestBody final ClienteDTO cliente){
        try {
            this.clienteServ.cadastrarCliente(cliente);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> edita(@PathVariable("id") final Long id, @RequestBody final ClienteDTO cliente){
        try {
            final Cliente cliente1 = this.clienteRep.findById(id).orElse(null);

            if (cliente1 == null || !cliente1.getId().equals(cliente.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }

            cliente1.setNome(cliente.getNome());
            this.clienteRep.save(cliente1);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            final Cliente cliente1 = this.clienteRep.findById(id).orElse(null);

            if (cliente1 == null){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            this.clienteServ.excluirCliente(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}

package br.com.pizzaria.controller;

import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.dto.FuncionarioDTO;
import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.repository.EnderecoRepository;
import br.com.pizzaria.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/endereco")
@CrossOrigin(origins = "http://localhost:4200")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRep;

    @Autowired
    private EnderecoService enderecoServ;

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable("id") final Long id) {
        final Endereco endereco = this.enderecoRep.findById(id).orElse(null);
        return ResponseEntity.ok(endereco);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Endereco>> list(){
        return ResponseEntity.ok(this.enderecoRep.findAll());

    }

    @PostMapping
    public ResponseEntity <String> cadastra(@RequestBody final EnderecoDTO endereco){
        try {
            this.enderecoServ.cadastrarEndereco(endereco);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> edita(@RequestBody final EnderecoDTO enderecoDTO){
        try {
            this.enderecoServ.atualizaEndereco(enderecoDTO);

            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (RuntimeException e){

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.enderecoServ.excluirEndereco(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



}

package br.com.pizzaria.controller;

import br.com.pizzaria.dto.ProdutoDTO;
import br.com.pizzaria.entity.Produto;
import br.com.pizzaria.repository.ProdutoRepository;
import br.com.pizzaria.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRep;

    @Autowired
    private ProdutoService produtoServ;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable("id") final Long id) {
        final Produto produto = this.produtoRep.findById(id).orElse(null);
        return ResponseEntity.ok(produto);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Produto>> list(){
        return ResponseEntity.ok(this.produtoRep.findAll());

    }


    @PostMapping
    public ResponseEntity <String> cadastra(@RequestBody final ProdutoDTO produto){
        try {
            this.produtoServ.cadastrarProduto(produto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> edita(@PathVariable("id") final Long id, @RequestBody final ProdutoDTO produto){
        try {
            final Produto produto1 = this.produtoRep.findById(id).orElse(null);

            if (produto1 == null || produto1.getId() != (produto.getId())){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            this.produtoServ.atualizaProduto(produto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.produtoServ.excluirProduto(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }



}

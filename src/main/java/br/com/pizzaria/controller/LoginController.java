package br.com.pizzaria.controller;

import br.com.pizzaria.dto.LoginDTO;
import br.com.pizzaria.entity.Login;
import br.com.pizzaria.repository.LoginRepository;
import br.com.pizzaria.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private LoginRepository loginRep;

    @Autowired
    private LoginService loginServ;

    @GetMapping("/{id}")
    public ResponseEntity<Login> findById(@PathVariable("id") final Long id) {
        final Login login = this.loginRep.findById(id).orElse(null);
        return ResponseEntity.ok(login);
    }
    @GetMapping("/lista")
    public ResponseEntity<List<Login>> list(){
        return ResponseEntity.ok(this.loginRep.findAll());

    }


    @PostMapping
    public ResponseEntity <String> cadastra(@RequestBody final LoginDTO login){
        try {
            this.loginServ.cadastrarLogin(login);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> edita(@PathVariable("id") final Long id, @RequestBody final LoginDTO login){
        try {
            final Login login1 = this.loginRep.findById(id).orElse(null);

            if (login1 == null || login1.getId() != (login.getId())){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            this.loginServ.atualizaLogin(login);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.loginServ.excluirLogin(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}

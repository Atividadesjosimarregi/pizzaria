package br.com.pizzaria.controller;

import br.com.pizzaria.dto.LoginDTO;
import br.com.pizzaria.entity.Cliente;
import br.com.pizzaria.entity.Login;
import br.com.pizzaria.repository.ClienteRepository;
import br.com.pizzaria.repository.LoginRepository;
import br.com.pizzaria.service.ClienteService;
import br.com.pizzaria.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/login")
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
    public ResponseEntity<List<Login>> List(){
        return ResponseEntity.ok(this.loginRep.findAll());

    }


    @PostMapping
    public ResponseEntity <?> cadastra(@RequestBody final LoginDTO login){
        try {
            this.loginServ.cadastrarLogin(login);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edita(@PathVariable("id") final Long id, @RequestBody final LoginDTO login){
        try {
            final Login login1 = this.loginRep.findById(id).orElse(null);

            if (login1 == null || login1.getId() != (login.getId())){
                return ResponseEntity.internalServerError().body("Nao foi possivel indentificar o registro informado");
            }
            this.loginServ.atualizaLogin(login);
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

            this.loginServ.excluirLogin(id);
            return ResponseEntity.ok("excluído");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


}

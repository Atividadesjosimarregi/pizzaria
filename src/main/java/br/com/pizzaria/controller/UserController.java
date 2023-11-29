package br.com.pizzaria.controller;

import br.com.pizzaria.dto.LoginDTO;
import br.com.pizzaria.dto.UserDTO;
import br.com.pizzaria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserService userServ;

    @PostMapping
    public ResponseEntity<UserDTO> logar(@RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.ok(userServ.logar(loginDTO));
        }catch(AuthenticationException ex) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("deslogar")
    public ResponseEntity<HttpStatus> logout() {

        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(null, HttpStatus.OK);

    }


}

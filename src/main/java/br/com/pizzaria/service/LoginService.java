package br.com.pizzaria.service;

import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.dto.LoginDTO;
import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.entity.Login;
import br.com.pizzaria.repository.EnderecoRepository;
import br.com.pizzaria.repository.LoginRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRep;



    @Transactional(rollbackFor = Exception.class)
    public void cadastrarLogin(final LoginDTO login){

        var logins = new Login();
        BeanUtils.copyProperties(login,logins);




        this.loginRep.save(logins);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaLogin(LoginDTO loginDTO) {

        Login loginExistente = this.loginRep.findById(loginDTO.getId()).orElse(null);


        if (loginExistente != null) {

            BeanUtils.copyProperties(loginDTO, loginExistente);


            this.loginRep.save(loginExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirLogin(final Long id){

        final Login loginBanco = this.loginRep.findById(id).orElse(null);

        if (loginBanco == null || loginBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o login informado.");
        }
        this.loginRep.delete(loginBanco);
    }
}

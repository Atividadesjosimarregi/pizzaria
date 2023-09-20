package br.com.pizzaria.service;

import br.com.pizzaria.dto.LoginDTO;
import br.com.pizzaria.entity.Login;
import br.com.pizzaria.repository.LoginRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRep;



    @Transactional(rollbackFor = Exception.class)
    public void cadastrarLogin(final LoginDTO login){

        var logins = new Login();
        BeanUtils.copyProperties(login,logins);

        Assert.isTrue(logins.getLoginn().length() <=40 ,"Login só pode ter até 40 caracteres");
        Assert.isTrue(logins.getLoginn() != null,"Login não pode ser nulo");
        Login existente = loginRep.findByLoginn(logins.getLoginn());
        Assert.isTrue( existente == null || existente.equals(logins.getLoginn()),"Login já existente");



        this.loginRep.save(logins);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaLogin(LoginDTO loginDTO) {

        Login loginExistente = this.loginRep.findById(loginDTO.getId()).orElse(null);


        if (loginExistente != null) {

            BeanUtils.copyProperties(loginDTO, loginExistente);

            Assert.isTrue(loginExistente.getLoginn().length() <=40 ,"Login só pode ter até 40 caracteres");
            Assert.isTrue(loginExistente.getLoginn() != null,"Login não pode ser nulo");


            this.loginRep.save(loginExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirLogin(final Long id){

        final Login loginBanco = this.loginRep.findById(id).orElse(null);

        if (loginBanco == null || loginBanco.getId()!=(id)){
            Assert.isTrue(2 == 3,"Não foi possivel identificar o registro informado");
        }
        this.loginRep.delete(loginBanco);
    }
}

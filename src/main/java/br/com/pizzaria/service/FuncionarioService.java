package br.com.pizzaria.service;


import br.com.pizzaria.dto.FuncionarioDTO;
import br.com.pizzaria.entity.Funcionario;
import br.com.pizzaria.repository.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRep;


    @Transactional(rollbackFor = Exception.class)
    public void cadastrarFuncionario(final FuncionarioDTO funcionario){

        var funcionarios = new Funcionario();
        BeanUtils.copyProperties(funcionario,funcionarios);

        Assert.isTrue(funcionarios.getNome() != null,"Nome não pode ser nulo");
        Assert.isTrue(funcionarios.getNome().length() <= 80,"Funcionario só pode ter até 80 caracters");

        this.funcionarioRep.save(funcionarios);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaFuncionario(FuncionarioDTO funcionarioDTO) {

        Funcionario funcionarioExistente = this.funcionarioRep.findById(funcionarioDTO.getId()).orElse(null);


        if (funcionarioExistente != null) {

            BeanUtils.copyProperties(funcionarioDTO, funcionarioExistente);

            Assert.isTrue(funcionarioExistente.getNome() != null,"Nome não pode ser nulo");
            Assert.isTrue(funcionarioExistente.getNome().length() <= 80,"Funcionario só pode ter até 80 caracters");


            this.funcionarioRep.save(funcionarioExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirFuncionario(final Long id){

        final Funcionario funcionarioBanco = this.funcionarioRep.findById(id).orElse(null);

        if (funcionarioBanco == null || funcionarioBanco.getId()!=(id)){
            Assert.isTrue(2 == 3 , "Não foi possivel identificar o registro informado");
        }
        this.funcionarioRep.delete(funcionarioBanco);
    }
}

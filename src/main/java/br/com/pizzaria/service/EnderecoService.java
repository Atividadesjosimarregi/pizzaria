package br.com.pizzaria.service;


import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.repository.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarEndereco(final EnderecoDTO endereco){

        var enderecos = new Endereco();
        BeanUtils.copyProperties(endereco,enderecos);

        Assert.isTrue(enderecos.getRua() != null,"Endereço não pode ser nulo");
        Assert.isTrue(enderecos.getRua().length() <=50,"Endereço só pode ter até 50 caracteres");

        Assert.isTrue(enderecos.getBairro() != null,"Bairro não pode ser nulo");
        Assert.isTrue(enderecos.getBairro().length() <=50,"Bairro só pode ter até 50 caracteres");

        Assert.isTrue(enderecos.getNumero() != 0,"Número não pode ser nulo");

        if(enderecos.getObservacao() != null) {
            Assert.isTrue(enderecos.getObservacao().length() <= 100, "Observação tem o máximo de 100 caracteres");
        }

        Assert.isTrue(enderecos.getCep() != null,"Cep não pode ser nulo");
        Assert.isTrue(enderecos.getCep().length() <= 15,"Cep só pode ter até 15 caracteres");








        this.enderecoRep.save(enderecos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaEndereco(EnderecoDTO enderecoDTO) {

        Endereco enderecoExistente = this.enderecoRep.findById(enderecoDTO.getId()).orElse(null);


        if (enderecoExistente != null) {

            BeanUtils.copyProperties(enderecoDTO, enderecoExistente);

            Assert.isTrue(enderecoExistente.getRua() != null,"Endereço não pode ser nulo");
            Assert.isTrue(enderecoExistente.getRua().length() <=50,"Endereço só pode ter até 50 caracteres");

            Assert.isTrue(enderecoExistente.getBairro() != null,"Bairro não pode ser nulo");
            Assert.isTrue(enderecoExistente.getBairro().length() <=50,"Bairro só pode ter até 50 caracteres");

            Assert.isTrue(enderecoExistente.getNumero() != 0,"Número não pode ser nulo");

            if(enderecoExistente.getObservacao() != null) {
                Assert.isTrue(enderecoExistente.getObservacao().length() <= 100, "Observação tem o máximo de 100 caracteres");
            }

            Assert.isTrue(enderecoExistente.getCep() != null,"Cep não pode ser nulo");
            Assert.isTrue(enderecoExistente.getCep().length() <= 15,"Cep só pode ter até 15 caracteres");

            this.enderecoRep.save(enderecoExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirEndereco(final Long id){

        final Endereco enderecoBanco = this.enderecoRep.findById(id).orElse(null);

        if (enderecoBanco == null || enderecoBanco.getId() !=(id)){
            Assert.isTrue(2 == 3 ,"Não foi possivel encontrar o registro informado");
        }
        this.enderecoRep.delete(enderecoBanco);
    }
}

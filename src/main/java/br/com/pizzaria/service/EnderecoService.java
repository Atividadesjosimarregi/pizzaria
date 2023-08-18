package br.com.pizzaria.service;


import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.repository.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarEndereco(final EnderecoDTO endereco){

        var enderecos = new Endereco();
        BeanUtils.copyProperties(endereco,enderecos);




        this.enderecoRep.save(enderecos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaEndereco(EnderecoDTO enderecoDTO) {

        Endereco enderecoExistente = this.enderecoRep.findById(enderecoDTO.getId()).orElse(null);


        if (enderecoExistente != null) {

            BeanUtils.copyProperties(enderecoDTO, enderecoExistente);


            this.enderecoRep.save(enderecoExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirEndereco(final Long id){

        final Endereco enderecoBanco = this.enderecoRep.findById(id).orElse(null);

        if (enderecoBanco == null || enderecoBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o endereco informado.");
        }
        this.enderecoRep.delete(enderecoBanco);
    }
}

package br.com.pizzaria.service;


import br.com.pizzaria.dto.SaborDTO;
import br.com.pizzaria.entity.Sabor;
import br.com.pizzaria.repository.SaborRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class SaborService {
    @Autowired
    private SaborRepository saborRep;



    @Transactional(rollbackFor = Exception.class)
    public void cadastrarSabor(final SaborDTO sabor){

        var sabors = new Sabor();
        BeanUtils.copyProperties(sabor,sabors);

        Assert.isTrue(sabors.getSaborr() != null,"Nome do sabor não pode ser nulo");
        Assert.isTrue(sabor.getSaborr().length() <= 100,"Nome pode ter até 100 caracteres");

        this.saborRep.save(sabors);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaSabor(SaborDTO saborDTO) {

        Sabor saborExistente = this.saborRep.findById(saborDTO.getId()).orElse(null);




        if (saborExistente != null) {

            BeanUtils.copyProperties(saborDTO, saborExistente);

            Assert.isTrue(saborExistente.getSaborr() != null,"Nome do sabor não pode ser nulo");
            Assert.isTrue(saborExistente.getSaborr().length() <= 100,"Nome pode ter até 100 caracteres");

            this.saborRep.save(saborExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirSabor(final Long id){

        final Sabor saborBanco = this.saborRep.findById(id).orElse(null);

        if (saborBanco == null || saborBanco.getId()!=(id)){
            Assert.isTrue(2 == 3, "Não foi possivel identificar o registro informado");
        }
        this.saborRep.delete(saborBanco);
    }
}

package br.com.pizzaria.service;


import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.dto.EstoqueDTO;
import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.entity.Estoque;
import br.com.pizzaria.repository.EnderecoRepository;
import br.com.pizzaria.repository.EstoqueRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRep;


    @Transactional(rollbackFor = Exception.class)
    public void cadastrarEstoque(final EstoqueDTO estoque){

        var estoques = new Estoque();
        BeanUtils.copyProperties(estoque,estoques);




        this.estoqueRep.save(estoques);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaEstoque(EstoqueDTO estoqueDTO) {

        Estoque estoqueExistente = this.estoqueRep.findById(estoqueDTO.getId()).orElse(null);


        if (estoqueExistente != null) {

            BeanUtils.copyProperties(estoqueDTO, estoqueExistente);


            this.estoqueRep.save(estoqueExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirEstoque(final Long id){

        final Estoque estoqueBanco = this.estoqueRep.findById(id).orElse(null);

        if (estoqueBanco == null || estoqueBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o estoque informado.");
        }
        this.estoqueRep.delete(estoqueBanco);
    }
}

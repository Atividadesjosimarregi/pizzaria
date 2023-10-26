package br.com.pizzaria.service;



import br.com.pizzaria.dto.EstoqueDTO;
import br.com.pizzaria.entity.Estoque;
import br.com.pizzaria.repository.EstoqueRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRep;


    @Transactional(rollbackFor = Exception.class)
    public void cadastrarEstoque(final EstoqueDTO estoqueDTO){

        var estoque = new Estoque();
        BeanUtils.copyProperties(estoqueDTO,estoque);

        float total;
        estoque.setQuantidade(1);


        estoque.setTotalProduto(estoque.getQuantidade() * estoque.getPreco());


        this.estoqueRep.save(estoque);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaEstoque(EstoqueDTO estoqueDTO) {

        Estoque estoqueExistente = this.estoqueRep.findById(estoqueDTO.getId()).orElse(null);


        if (estoqueExistente != null) {

            BeanUtils.copyProperties(estoqueDTO, estoqueExistente);

            Assert.isTrue(estoqueExistente.getNome() != null,"Nome não pode ser nulo");
            Assert.isTrue(estoqueExistente.getNome().length() <=150,"Nome pode ter até 150 caracteres");

            Assert.isTrue(estoqueExistente.getPreco() != 0, "Preço não pode ser nulo");

            this.estoqueRep.save(estoqueExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirEstoque(final Long id){

        final Estoque estoqueBanco = this.estoqueRep.findById(id).orElse(null);

        if (estoqueBanco == null || estoqueBanco.getId()!=(id)){
            Assert.isTrue(2 == 3 ,"Não foi possivel identificar o registro informado");
        }
        this.estoqueRep.delete(estoqueBanco);
    }
}

package br.com.pizzaria.service;


import br.com.pizzaria.dto.ProdutoDTO;
import br.com.pizzaria.entity.Estoque;
import br.com.pizzaria.entity.Produto;
import br.com.pizzaria.repository.EstoqueRepository;
import br.com.pizzaria.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRep;

    @Autowired
    private EstoqueRepository estoqueRep;




    @Transactional(rollbackFor = Exception.class)
    public void cadastrarProduto(final ProdutoDTO produto){

        var produtos = new Produto();
        float total = 0;
        BeanUtils.copyProperties(produto,produtos);

        Assert.isTrue(produtos.getQuantidade() != 0 ,"A quantidade não pode ser nulo");



        produtos.setPrecoProduto(total);

        this.produtoRep.save(produtos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaProduto(ProdutoDTO produtoDTO) {

        Produto produtoExistente = this.produtoRep.findById(produtoDTO.getId()).orElse(null);


        if (produtoExistente != null) {

            BeanUtils.copyProperties(produtoDTO, produtoExistente);

            Assert.isTrue(produtoExistente.getQuantidade() != 0 ,"A quantidade não pode ser nulo");


            this.produtoRep.save(produtoExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirProduto(final Long id){

        final Produto produtoBanco = this.produtoRep.findById(id).orElse(null);

        if (produtoBanco == null || produtoBanco.getId()!=(id)){
            Assert.isTrue(2 == 3,"Não foi possivel identificar o registro informado");
        }
        this.produtoRep.delete(produtoBanco);
    }
}

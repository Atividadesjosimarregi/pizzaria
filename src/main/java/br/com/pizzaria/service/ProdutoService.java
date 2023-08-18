package br.com.pizzaria.service;

import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.dto.ProdutoDTO;
import br.com.pizzaria.entity.Endereco;
import br.com.pizzaria.entity.Produto;
import br.com.pizzaria.repository.EnderecoRepository;
import br.com.pizzaria.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRep;



    @Transactional(rollbackFor = Exception.class)
    public void cadastrarProduto(final ProdutoDTO produto){

        var produtos = new Produto();
        BeanUtils.copyProperties(produto,produtos);




        this.produtoRep.save(produtos);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaProduto(ProdutoDTO produtoDTO) {

        Produto produtoExistente = this.produtoRep.findById(produtoDTO.getId()).orElse(null);


        if (produtoExistente != null) {

            BeanUtils.copyProperties(produtoDTO, produtoExistente);


            this.produtoRep.save(produtoExistente);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirProduto(final Long id){

        final Produto produtoBanco = this.produtoRep.findById(id).orElse(null);

        if (produtoBanco == null || produtoBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o produto informado.");
        }
        this.produtoRep.delete(produtoBanco);
    }
}

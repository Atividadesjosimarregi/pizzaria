package br.com.pizzaria;

import br.com.pizzaria.dto.ProdutoDTO;
import br.com.pizzaria.entity.Estoque;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 class ProdutoDtoTests {

    Estoque estoques = new Estoque(1L,12,"coca");
    ProdutoDTO produto = new ProdutoDTO(2,estoques,24);

    ProdutoDTO produto2 = new ProdutoDTO();

    @Test
    void getsetNome(){
        produto.setQuantidade(1);
        Assertions.assertEquals(1, produto.getQuantidade());
    }

    @Test
    void getsetPreco(){
        produto.setPrecoProduto(25);
        Assertions.assertEquals(25, produto.getPrecoProduto());
    }
    @Test
    void testconstrutorvazio(){
        ProdutoDTO produto3 = new ProdutoDTO();
        Assertions.assertEquals(produto2,produto3);
    }

    @Test
    void testeid(){

        produto.setId(2L);
        Assertions.assertEquals(2, produto.getId());
    }

    @Test
    void testeComparacao(){
        ProdutoDTO produto2 = new ProdutoDTO(2,estoques,24);
        Assertions.assertEquals(produto,produto2);
    }



    @Test
    void testHashCode() {
        ProdutoDTO produto1 = new ProdutoDTO(2,estoques,24);
        ProdutoDTO produto2 = new ProdutoDTO(2,estoques,24);


        Assertions.assertEquals(produto1.hashCode(), produto2.hashCode());;

    }

    @Test
    void testToString() {
        ProdutoDTO produto2 = new ProdutoDTO(2,estoques,24);
        Assertions.assertEquals(produto2.toString(), produto.toString());


    }


}

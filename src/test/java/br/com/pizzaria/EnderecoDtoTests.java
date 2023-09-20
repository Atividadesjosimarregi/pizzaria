package br.com.pizzaria;

import br.com.pizzaria.dto.EnderecoDTO;
import br.com.pizzaria.entity.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnderecoDtoTests {

    Cliente cliente = new Cliente(1L,"Josimar");
    EnderecoDTO endereco = new EnderecoDTO("Dois vizinhos","Jardim ipe",321,"Casa vermelha","3499525-3",cliente);

    @Test
    void getsetNomeRua(){
        endereco.setRua("Laranjeira");
        Assertions.assertEquals("Laranjeira", endereco.getRua());
    }
    @Test
    void getsetNomeBairro(){
        endereco.setBairro("Lancaster");
        Assertions.assertEquals("Lancaster", endereco.getBairro());
    }

    @Test
    void getsetNumeroCasa(){
        endereco.setNumero(824);
        Assertions.assertEquals(824, endereco.getNumero());
    }

    @Test
    void getsetObservacaoCasa(){
        endereco.setObservacao("Casa amarela");
        Assertions.assertEquals("Casa amarela", endereco.getObservacao());
    }

    @Test
    void getsetCEP(){
        endereco.setCep("231151-4");
        Assertions.assertEquals("231151-4", endereco.getCep());
    }

    @Test
    void testeid(){

        endereco.setId(2L);
        Assertions.assertEquals(2, endereco.getId());
    }

    @Test
    void testeComparacao(){
        EnderecoDTO endereco2 = new EnderecoDTO("Dois vizinhos","Jardim ipe",321,"Casa vermelha","3499525-3",cliente);
        Assertions.assertEquals(endereco,endereco2);
    }

    @Test
    void testHashCode() {
        EnderecoDTO endereco1 = new EnderecoDTO("Dois vizinhos","Jardim ipe",321,"Casa vermelha","3499525-3",cliente);
        EnderecoDTO endereco2 = new EnderecoDTO("Dois vizinhos","Jardim ipe",321,"Casa vermelha","3499525-3",cliente);


        Assertions.assertEquals(endereco1.hashCode(), endereco2.hashCode());;

    }

    @Test
    void testToString() {
        EnderecoDTO endereco2 = new EnderecoDTO("Dois vizinhos","Jardim ipe",321,"Casa vermelha","3499525-3",cliente);
        Assertions.assertEquals(endereco2.toString(), endereco.toString());


    }






}

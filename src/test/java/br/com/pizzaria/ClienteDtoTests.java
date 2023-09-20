package br.com.pizzaria;
import br.com.pizzaria.dto.ClienteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class ClienteDtoTests {

    ClienteDTO cliente = new ClienteDTO("Josimar");



    @Test
    void getsetNome(){
        cliente.setNome("Reginaldo");
        Assertions.assertEquals("Reginaldo", cliente.getNome());
    }

    @Test
    void testeid(){

        cliente.setId(2L);
        Assertions.assertEquals(2, cliente.getId());
    }

    @Test
    void testeComparacao(){
        ClienteDTO cliente2 = new ClienteDTO("Josimar");
        Assertions.assertEquals(cliente,cliente2);
    }



    @Test
    void testHashCode() {
        ClienteDTO cliente1 = new ClienteDTO("Josimar");
        ClienteDTO cliente2 = new ClienteDTO("Josimar");


        Assertions.assertEquals(cliente1.hashCode(), cliente2.hashCode());;

    }

    @Test
    void testToString() {
        ClienteDTO cliente2 = new ClienteDTO("Josimar");
        Assertions.assertEquals(cliente2.toString(), cliente.toString());


    }




}
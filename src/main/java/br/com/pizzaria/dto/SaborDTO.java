package br.com.pizzaria.dto;


import lombok.Data;


@Data
public class SaborDTO extends AbstractDTO{


    private String saborr;

    public SaborDTO(){

    }
    public SaborDTO(String saborr) {
        this.saborr = saborr;
    }
}

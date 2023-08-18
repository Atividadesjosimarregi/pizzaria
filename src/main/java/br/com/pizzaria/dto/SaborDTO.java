package br.com.pizzaria.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SaborDTO extends AbstractDTO{


    private String sabor;

}

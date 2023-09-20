package br.com.pizzaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sabores")
public class Sabor extends AbstractEntity {

    @Getter @Setter
    @Column(name = "sabor",nullable = false,unique = true,length = 100)
    private String saborr;

    public Sabor(){

    }
    public Sabor(Long id,String saborr) {
        this.id = id;
        this.saborr = saborr;
    }
}

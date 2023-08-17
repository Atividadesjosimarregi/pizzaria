package br.com.pizzaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sabores")
public class Sabor extends abstractEntity{

    @Getter @Setter
    @Column(name = "sabor",nullable = false,unique = true)
    private String sabor;


}

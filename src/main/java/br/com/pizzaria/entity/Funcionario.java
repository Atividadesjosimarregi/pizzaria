package br.com.pizzaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "funcionarios",schema = "public")
public class Funcionario extends abstractEntity{

    @Getter @Setter
    @Column(name = "nome",nullable = false,length = 80)
    private String nome;
}

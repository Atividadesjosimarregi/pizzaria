package br.com.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clientes", schema = "public")
public class Cliente extends abstractEntity{

    @Getter @Setter
    @Column(name = "nome",nullable = false, length = 50)
    private String nome;

    @Getter @Setter
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<Endereco> enderecos;
}

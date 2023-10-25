package br.com.pizzaria.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clientes", schema = "public")
public class Cliente extends AbstractEntity {

    @Getter @Setter
    @Column(name = "nome",nullable = false, length = 50)
    private String nome;

    @Getter @Setter
    @JsonManagedReference
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER,cascade = CascadeType.ALL )
    private List<Endereco> enderecos;

    public Cliente() {
    }

    public Cliente(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}

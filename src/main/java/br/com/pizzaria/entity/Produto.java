package br.com.pizzaria.entity;

import com.zaxxer.hikari.util.ClockSource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Entity
@Table(name = "produtos",schema = "public")
public class Produto extends abstractEntity{

    @Getter @Setter
    @Column(name = "quantidade",nullable = false)
    private int quantidade;

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "produto_estoque",
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {
                            "produto_id",
                            "estoque_id"
                    }
            ),
            joinColumns = @JoinColumn(
                    name = "produto_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "estoque_id"
            )
    )
    private List<Estoque> estoques;

}

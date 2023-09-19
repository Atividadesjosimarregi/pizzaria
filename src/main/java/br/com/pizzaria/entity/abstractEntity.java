package br.com.pizzaria.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ValueGenerationType;

@MappedSuperclass
public class abstractEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name ="id",nullable = false,unique = true)
    protected Long id;



}

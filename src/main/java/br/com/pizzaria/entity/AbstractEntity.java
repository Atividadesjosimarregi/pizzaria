package br.com.pizzaria.entity;


import jakarta.persistence.*;
import lombok.Getter;


@MappedSuperclass
public class AbstractEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name ="id",nullable = false,unique = true)
    protected Long id;



}

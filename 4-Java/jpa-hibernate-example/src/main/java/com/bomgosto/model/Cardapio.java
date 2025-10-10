package com.bomgosto.model;

import javax.persistence.*;

@Entity
public class Cardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cardapio;

    @Column(unique = true)
    private String nome;
    private String descricao;
    private Double preco_unitario;

    public Long getId_cardapio() {
        return id_cardapio;
    }

    public void setId_cardapio(Long id_cardapio) {
        this.id_cardapio = id_cardapio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(Double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }
}

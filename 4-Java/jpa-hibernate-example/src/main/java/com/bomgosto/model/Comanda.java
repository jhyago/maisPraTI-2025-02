package com.bomgosto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_comanda;
    private String nome_cliente;
    private Date data;
    private int nr_mesa;

    public long getId_comanda() {
        return id_comanda;
    }

    public int getNr_mesa() {
        return nr_mesa;
    }

    public void setNr_mesa(int nr_mesa) {
        this.nr_mesa = nr_mesa;
    }

    public void setId_comanda(long id_comanda) {
        this.id_comanda = id_comanda;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}

package model;

import javax.persistence.*;

@Entity
public class Item_comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_item_comanda;

    @ManyToOne
    @JoinColumn(name = "id_comanda")
    private Comanda comanda;

    @ManyToOne
    @JoinColumn(name = "id_cardapio")
    private Cardapio cardapio;
    private int quantidade;

    public Long getId_item_comanda() {
        return id_item_comanda;
    }

    public void setId_item_comanda(Long id_item_comanda) {
        this.id_item_comanda = id_item_comanda;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

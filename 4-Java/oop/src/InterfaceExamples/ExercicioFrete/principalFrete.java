package InterfaceExamples.ExercicioFrete;

import java.math.BigDecimal;

public class principalFrete {
    public static void main(String[] args) {
        var pedido = new Pedido(new BigDecimal("2.5"), new BigDecimal("120"));

        var padrao = Frete.of("padrao");
        var expresso = Frete.of("expresso");

        System.out.println("Padrão -> R$ " + padrao.calcular(pedido));
        System.out.println("Expresso -> R$ " + expresso.calcular(pedido));
    }
}

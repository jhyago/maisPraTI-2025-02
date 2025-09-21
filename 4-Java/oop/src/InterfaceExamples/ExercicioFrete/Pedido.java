package InterfaceExamples.ExercicioFrete;

import java.math.BigDecimal;
import java.util.Objects;

public record Pedido(BigDecimal pesoKg, BigDecimal distanciaKM) {
    public Pedido {
        Objects.requireNonNull(pesoKg);
        Objects.requireNonNull(distanciaKM);

        if(pesoKg.compareTo(BigDecimal.ZERO) <= 0) {throw new IllegalArgumentException("O Peso deve ser maior que zero");}

        if(distanciaKM.compareTo(BigDecimal.ZERO) <= 0) {throw new IllegalArgumentException("A Distância deve ser maior que zero");}
    }
}

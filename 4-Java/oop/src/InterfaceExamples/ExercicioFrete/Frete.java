package InterfaceExamples.ExercicioFrete;

import java.math.BigDecimal;

public interface Frete {
    BigDecimal calcular(Pedido p);

    default BigDecimal arrendonda(BigDecimal valor) {
        return valor.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    static Frete of(String tipo) {
        return switch(tipo.toLowerCase()) {
            case "padrao", "padrão" -> new FretePadrao();
            case "expresso" -> new FreteExpresso();
            default -> throw new IllegalArgumentException("Tipo invalido");
        };
    }
}

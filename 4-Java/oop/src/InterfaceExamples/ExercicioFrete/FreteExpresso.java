package InterfaceExamples.ExercicioFrete;

import java.math.BigDecimal;

public final class FreteExpresso implements Frete {
    @Override
    public BigDecimal calcular(Pedido pedido) {
        var total = new BigDecimal("20.00")
                .add(pedido.pesoKg().multiply(new BigDecimal("1.80")))
                .add(pedido.distanciaKM().multiply(new BigDecimal("0.15")));
        return arrendonda(total);
    }
}

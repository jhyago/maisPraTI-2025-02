package InterfaceExamples.ExercicioNotificacao;

public interface EnviadorNotificacao {
    void enviar(String destino, String mensagem);
}

// Implemente um cálculo de frete para dois tipos: padrão e expresso.
//
// O usuário informa peso (kg) e distância (km).
//
//        - Cada tipo tem uma fórmula própria.
//
//        - Use uma interface InterfaceExamples.Frete com:
//
//        - BigDecimal calcular(InterfaceExamples.ExercicioFrete.Pedido p)
//
// default BigDecimal arredonda(BigDecimal v) (2 casas, HALF_UP)
//
//        - static InterfaceExamples.Frete of(String tipo) que retorna a implementação correta ("padrao" ou "expresso")
//
// Padrão: base R$10, + R$1,20 por kg, + R$0,10 por km

// Expresso: base R$20, + R$1,80 por kg, + R$0,15 por km

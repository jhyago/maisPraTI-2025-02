package exercicio3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Funcionario gerente = new Gerente("Ronan", new BigDecimal("10000"));
        Funcionario desenvolvedor = new Desenvolvedor("Lucas", new BigDecimal("6000"));

        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(gerente);
        funcionarios.add(desenvolvedor);

        for(Funcionario funcionario : funcionarios) {
            System.out.println("Funcionário: " + funcionario.getNome());
            System.out.println("Salário: " + funcionario.getSalario());
            System.out.println("Bônus: " + funcionario.calcularBonus());
            System.out.println("=========");
        }
    }
}

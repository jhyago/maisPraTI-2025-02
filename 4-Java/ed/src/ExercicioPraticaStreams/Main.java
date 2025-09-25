package ExercicioPraticaStreams;                               // Pacote do exercício.

import java.text.NumberFormat;                                 // Para formatar salário em moeda BR.
import java.util.Comparator;                                   // Comparadores para ordenação.
import java.util.List;                                         // Lista imutável (List.of).
import java.util.Locale;                                       // Locale pt-BR para moeda.
import java.util.stream.Collectors;                            // Para coletar resultados (quando útil).

/**
 * Demonstra operações com Collections e Streams:
 * - Filtro por idade > 30 (imprime nome e cargo)
 * - Ordenação por salário crescente (imprime nome e salário)
 * - Média salarial
 * - Contagem de salários > 4000
 * - Lista com nomes dos funcionários
 * Tudo empregando Streams de forma concisa e eficiente.
 */
public class Main {
    public static void main(String[] args) {
        // Formatador de moeda no padrão brasileiro.
        NumberFormat BRL = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        // Cria a lista de 10 funcionários com dados variados.
        // (List.of é imutável; se precisar alterar, use ArrayList)
        List<Funcionario> funcionarios = List.of(
            new Funcionario("Alucard",       50_000, "Vampiro",               2000),
            new Funcionario("Peter Parker",   1_200, "Fotógrafo",               20),
            new Funcionario("Mary Jane",      3_000, "Atriz",                   21),
            new Funcionario("Bruce Wayne",   30_000, "CEO",                     40),
            new Funcionario("Cloud Strife",   1_000, "Soldier",                 19),
            new Funcionario("Johnny Storm",   5_000, "Herói",                   23),
            new Funcionario("Ronaldo",        3_000, "Desenvolvedor Júnior",    19),
            new Funcionario("Marcos",         5_000, "Desenvolvedor Pleno",     21),
            new Funcionario("Édison",         7_000, "Desenvolvedor Sênior",    23),
            new Funcionario("Paula",         10_000, "Tech Lead",               24)
        );

        // 1) Filtre e exiba os nomes e cargos dos funcionários com mais de 30 anos.
        System.out.println("Funcionários com mais de 30 anos:");
        funcionarios.stream()
                .filter(f -> f.getIdade() > 30)                                  // Mantém apenas idade > 30.
                .forEach(f -> System.out.println(f.getNome() + " — " + f.getCargo())); // Imprime nome e cargo.

        // 2) Ordene os funcionários por salário em ordem crescente e exiba os nomes e salários.
        System.out.println("\nOrdenar funcionários por salário (crescente):");
        funcionarios.stream()
                .sorted(Comparator.comparingDouble(Funcionario::getSalario))     // Ordena por salário crescente.
                .forEach(f -> System.out.println(f.getNome() + " — " + BRL.format(f.getSalario()))); // Nome + salário formatado.

        // 3) Calcule e exiba a média salarial dos funcionários.
        double mediaSalarial = funcionarios.stream()
                .mapToDouble(Funcionario::getSalario)                            // Converte para DoubleStream.
                .average()                                                       // Calcula média.
                .orElse(0.0);                                                    // Se lista vazia, retorna 0.0.
        System.out.println("\nMédia salarial da empresa: " + BRL.format(mediaSalarial));

        // 4) Exiba o total de funcionários com salário superior a R$ 4.000,00.
        long totalAcima4000 = funcionarios.stream()
                .filter(f -> f.getSalario() >
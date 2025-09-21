package ExercicioPraticaStreams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
//  Crie uma classe chamada Funcionario com os seguintes atributos: nome (String), salario (double), cargo (String) e idade (int). Implemente o construtor, os métodos getters e setters necessários.

//  Em seguida, crie uma lista de 10 funcionários, com valores variados para os atributos, e realize as seguintes operações utilizando Collections e Streams:

//  Filtre e exiba os nomes e cargos dos funcionários com mais de 30 anos.

//  Ordene os funcionários por salário em ordem crescente e exiba os nomes e salários.

//  Calcule e exiba a média salarial dos funcionários.

//  Exiba o total de funcionários com salário superior a R$ 4.000,00.

//  Crie uma lista com os nomes dos funcionários e a exiba.

//  Utilize os recursos de Streams para realizar as operações de forma concisa e eficiente.
public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = Arrays.asList(
          new Funcionario("Alucard", 50000, "Vampiro", 2000),
          new Funcionario("Peter Parker", 1200, "Fotógrafo", 20),
          new Funcionario("Mary Jane", 3000, "Atriz", 21),
          new Funcionario("Bruce Wayne", 30000, "CEO", 40),
          new Funcionario("Cloud Strife", 1000, "Soldier", 19),
          new Funcionario("Johnny Storm", 5000, "Herói", 23),
          new Funcionario("Ronaldo", 3000, "Desenvolvedor Júnior", 19),
          new Funcionario("Marcos", 5000, "Desenvolvedor Pleno", 21),
          new Funcionario("Édison", 7000, "Desenvolvedor Sênior", 23),
          new Funcionario("Paula", 10000, "Tech Lead", 24)
        );

        //  Filtre e exiba os nomes e cargos dos funcionários com mais de 30 anos.
        System.out.println("Funcionários com mais de 30 anos: ");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getIdade() > 30)
                .forEach(funcionario -> System.out.println(funcionario.getNome() + ": " + funcionario.getCargo()));
        //Ordene os funcionários por salário em ordem crescente e exiba os nomes e salários.
        System.out.println("\nOrdenar funcionários por salário em ordem crescente: ");
        funcionarios.stream()
                .sorted(Comparator.comparingDouble(Funcionario::getSalario))
                .forEach(funcionario -> System.out.println(funcionario.getNome() + ": " + funcionario.getSalario()));
        //Calcule e exiba a média salarial dos funcionários.
        double mediaSalarial = funcionarios.stream()
                .mapToDouble(Funcionario::getSalario)
                .average()
                .orElse(0.0);
        System.out.println("Média salarial da empresa: " + mediaSalarial);

        //Exiba o total de funcionários com salário superior a R$ 4.000,00.
        long totFuncionariosAcima4000 = funcionarios.stream()
                .filter(f -> f.getSalario() > 4000)
                .count();
        System.out.println("Total de funcionários com o salário maior de 4000 é: " + + totFuncionariosAcima4000);

        //Crie uma lista com os nomes dos funcionários e a exiba.
        List<String> nomesFuncionarios = funcionarios.stream()
                .map(Funcionario::getNome)
                .collect(Collectors.toList());
        System.out.println("Lista de nomes dos funcionários");
        nomesFuncionarios.forEach(System.out::println);
    }
}

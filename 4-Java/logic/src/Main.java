import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        int intVar = 2147483647;
//        long longVar = 9223372036854775807L;
//        byte byteVar = 127;
//        short shortVar = 32767;
//
//        double doubleVar = 9223372036854775807.0;
//        float floatVar = 9223372036854775807.0f;
//
//        char charVar = 'A';
//
//        boolean booleanVar = true;
//
//        System.out.println("intVar = " + intVar);
//        System.out.println("longVar = " + longVar);
//        System.out.println("byteVar = " + byteVar);
//        System.out.println("shortVar = " + shortVar);
//        System.out.println("doubleVar = " + doubleVar);
//        System.out.println("floatVar = " + floatVar);
//        System.out.println("charVar = " + charVar);
//        System.out.println("booleanVar = " + booleanVar);
//
//        System.out.printf("%.2f \n", floatVar);
//
//        String name = "Jaques";
//        System.out.println(name);
//
//        Locale.setDefault(Locale.US);
//        System.out.printf("%.2f \n", floatVar);

//        Locale ptBR = new Locale("pt", "BR");
//        Locale us = Locale.US;
//
//        Locale atual = Locale.getDefault();
//        System.out.println(atual);
//
//        Locale.setDefault(new Locale("pt", "BR"));
//        Locale atual2 = Locale.getDefault();
//        System.out.println(atual2);
//        double valor = 1234.56;
//
//        NumberFormat formato = NumberFormat.getCurrencyInstance(ptBR);
//        NumberFormat formato2 = NumberFormat.getCurrencyInstance(us);
//
//        System.out.println(formato.format(valor));
//        System.out.println(formato2.format(valor));

//        int a, b, c;
//        double area;
//
//        a = '5';
//        b = 7;
//        c = 3;
//
//        area = (double) (a + b) / 2 * c;
//        System.out.println(area);

        Scanner input = new Scanner(System.in);

//        int numero = input.nextInt();
//        System.out.println("Numero: " + numero);

        //Exercício 1: Conversão de Celsius para Fahrenheit
//        double celsius = input.nextDouble();
//        double farenheit = celsius * 1.8 + 32;
//        System.out.println("Temperatura em Farenheit é: " + farenheit);

        //Exercício 2: Verificação de Maior Idade
//        int idade = 19;
//
//        if(idade >= 18){
//            System.out.println("Você é maior de idade!");
//        } else {
//            System.out.println("Você não é maior de idade!");
//        }

        //Exercício 3: Par ou ímpar
//        int numero = 4;
//        if (numero % 2 == 0) {
//            System.out.println("Número PAR!");
//        } else {
//            System.out.println("Número ÍMPAR!");
//        }

        //Exercício 4: Dia da semana usando SWITCH-CASE
//        System.out.println("Digite o número da semana: ");
//        int dia = input.nextInt();
//
//        switch (dia) {
//            case 1:
//                System.out.println("Domingo");
//                break;
//            case 2:
//                System.out.println("Segunda-feira");
//                break;
//            case 3:
//                System.out.println("Terça-feira");
//                break;
//            default:
//                System.out.println("Dia inválido!");
//                break;
//        }

//        //Exerício 5: Calculadora Simples
//        System.out.println("Digite um número: ");
//        double num1 = input.nextDouble();
//        System.out.println("Digite o segundo número: ");
//        double num2 = input.nextDouble();
//        System.out.println("Digite um operador (+, -, /, *):");
//        char operador = input.next().charAt(0);
//        double resultado = 0;
//        switch (operador) {
//            case '+':
//                resultado = num1 + num2;
//                break;
//            case '-':
//                resultado = num1 - num2;
//                break;
//            case '/':
//                if (num2 != 0) {
//                    resultado = num1 / num2;
//                } else {
//                    System.out.println("Erro: Divisão por zero não permitida!");
//                }
//                break;
//            case '*':
//                resultado = num1 * num2;
//                break;
//            default:
//                System.out.println("Operador inválido!");
//                break;
//        }
//
//        System.out.println("Resultado: " + resultado);

        // Contar de 1 até 10 com For

//        for (int i = 1; i <= 10; i++) {
//            System.out.println(i);
//        }
//        int i = 1;
//        while(i <= 10) {
//            System.out.println("Valor: " + i);
//            i++;
//        }
//        int i = 1;
//        do {
//            System.out.println(i++);
//        } while(i <= 10);
//
//        String texto = "Grêmio é maior que aquele time";
//        int comprimento = texto.length();
//        System.out.println(comprimento);
//
//        String maisucula = texto.toUpperCase();
//        System.out.println(maisucula);

//        String minuscula = texto.toLowerCase();
//        System.out.println(minuscula);
//
//        boolean contem = texto.contains("Grêmio");
//        System.out.println(contem);
//
//        String substituto = texto.replace("time", "Grupo");
//        System.out.println(substituto);
//
//        String substring = texto.substring(0, 6);
//        System.out.println(substring);

//        double num = 50.30;
//        double arrendodado = Math.round(num);
//        System.out.println(arrendodado);
//
//        System.out.println(Math.ceil(num));
//        System.out.println(Math.floor(num));
//        System.out.println(Math.round(num));
//        System.out.println(Math.sqrt(num));
//        System.out.println(Math.abs(num));
//        System.out.println(Math.random());

//        int [] vetor = new int[10];
//        vetor[0] = 1;
//        vetor[1] = 2;
//        vetor[2] = 3;
//        vetor[3] = 4;
//        vetor[4] = 5;
//        vetor[5] = 6;
//        vetor[6] = 7;
//        vetor[7] = 8;
//        vetor[8] = 9;
//        vetor[9] = 10;
//
//        System.out.println("Elementos do Array: ");
//        for (int i = 0; i < vetor.length; i++) {
//            System.out.println(vetor[i]);
//        }

//        int soma = 0;
//        for (int i = 0; i < vetor.length; i++) {
//            soma += vetor[i];
//        }
//
//        System.out.println(soma);

        // Exercício 6: encontrar o maior número de um array

//        int max = vetor[0];
//
//        for (int i = 1; i < vetor.length; i++) {
//            if (vetor[i] > max) {
//                max = vetor[i];
//            }
//        }
//
//        System.out.println(max);

        // Exercício 7 - Adicionem um elemento ao Array

//        int [] newVetor = new int[vetor.length];
//        for (int i = 0; i < vetor.length; i++) {
//            newVetor[i] = vetor[i];
//        }
//
//        newVetor[vetor.length] = 1;

//        int [][] matrix = {
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9}
//        };
//
//        for(int i = 0; i < matrix.length; i++) {
//            for(int j = 0; j < matrix[i].length; j++) {
//                System.out.println(matrix[i][j]);
//            }
//        }

        // Exercício 8 - Soma das diagonais da matriz


    }
}

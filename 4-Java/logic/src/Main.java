import java.math.BigDecimal; // Importa a classe para cálculos de alta precisão (dinheiro, etc.)
import java.text.NumberFormat; // Importa formatador numérico/moeda
import java.util.Arrays; // Importa utilitário para arrays (sort, toString, etc.)
import java.util.Locale; // Importa configuração de localidade (idioma, moeda, formatação)
import java.util.Scanner; // Importa leitura de dados do teclado
import java.util.ArrayList; // Importa lista dinâmica
import java.util.List; // Interface de listas

public class Main { // Declara a classe pública Main (o nome do arquivo deve ser Main.java)

    public static void main(String[] args) { // Ponto de entrada do programa
        // Chama demonstrações básicas de tipos primitivos e impressão
        demoTiposPrimitivos();

        // Chama demonstração de Locale e NumberFormat
        demoLocaleEFormatacao();

        // Exercício 1: Celsius -> Fahrenheit (com valor de exemplo)
        exercicio1CelsiusParaFahrenheit(25.0);

        // Exercício 2: Verificação de maioridade (com valor de exemplo)
        exercicio2MaiorIdade(19);

        // Exercício 3: Par ou ímpar (com valor de exemplo)
        exercicio3ParOuImpar(4);

        // Exercício 4: Dia da semana com switch-case (com valor de exemplo)
        exercicio4DiaDaSemana(3);

        // Exercício 5: Calculadora simples (com valores de exemplo)
        exercicio5CalculadoraSimples(10.0, 5.0, '+');
        exercicio5CalculadoraSimples(10.0, 5.0, '-');
        exercicio5CalculadoraSimples(10.0, 5.0, '*');
        exercicio5CalculadoraSimples(10.0, 0.0, '/');

        // Demonstrações de laços: for, while, do-while
        demoLacos();

        // Demonstrações com Strings (tamanho, maiúscula, minúscula, contains, replace, substring)
        demoStrings();

        // Demonstrações com Math (arredondamentos, raiz, absoluto, aleatório)
        demoFuncoesMatematicas();

        // Demonstrações com Arrays unidimensionais
        demoArrays1D();

        // Exercício 6: Encontrar o maior número de um array
        exercicio6MaiorNumeroEmArray(new int[]{5, 17, 2, 42, 9, 42});

        // Exercício 7: Adicionar um elemento ao array (criando novo array)
        exercicio7AdicionarElementoNoArray(new int[]{1, 2, 3, 4, 5}, 99);

        // Demonstrações com matrizes 2D
        demoMatriz2D();

        // Exercício 8: Soma das diagonais da matriz
        exercicio8SomaDasDiagonais(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });

        // Demonstração de BigDecimal para cálculos monetários
        demoBigDecimalFinanceiro();

        // Demonstração de Arrays.sort, Arrays.binarySearch e for-each
        demoOrdenacaoEBusca();

        // Demonstração com List (ArrayList) e operações básicas
        demoCollectionsList();

        // Exemplo extra: funções utilitárias (palíndromo e reverso de String)
        demoFuncoesUtilitarias();

        // Exemplo: uso opcional de Scanner (comentado para não travar execução)
        // demoScannerOpcional();
    } // Fim do main

    // =================== DEMOS BÁSICAS ===================

    private static void demoTiposPrimitivos() { // Método para demonstrar tipos primitivos
        int inteiroMaximo = 2_147_483_647; // Declara int com valor máximo (separadores para legibilidade)
        long longoMaximo = 9_223_372_036_854_775_807L; // Declara long com sufixo L
        byte byteMaximo = 127; // Declara byte com maior valor (8 bits)
        short shortMaximo = 32_767; // Declara short com maior valor (16 bits)
        double duploExemplo = 3.1415926535; // Declara double com casas decimais
        float flutuanteExemplo = 2.71828f; // Declara float com sufixo f
        char caractereExemplo = 'A'; // Declara char com um caractere
        boolean booleanoVerdadeiro = true; // Declara boolean com valor true

        System.out.println("=== Tipos Primitivos ==="); // Imprime título da seção
        System.out.println("int = " + inteiroMaximo); // Imprime valor int
        System.out.println("long = " + longoMaximo); // Imprime valor long
        System.out.println("byte = " + byteMaximo); // Imprime valor byte
        System.out.println("short = " + shortMaximo); // Imprime valor short
        System.out.println("double = " + duploExemplo); // Imprime valor double
        System.out.println("float = " + flutuanteExemplo); // Imprime valor float
        System.out.println("char = " + caractereExemplo); // Imprime valor char
        System.out.println("boolean = " + booleanoVerdadeiro); // Imprime valor boolean

        System.out.printf("Float formatado com 2 casas: %.2f\n", flutuanteExemplo); // Formata float
        String nomeExemplo = "Jaques"; // Declara String com nome
        System.out.println("Nome: " + nomeExemplo); // Imprime a String
        System.out.println(); // Linha em branco
    }

    private static void demoLocaleEFormatacao() { // Método para demonstrar Locale e formatação
        Locale localPtBr = new Locale("pt", "BR"); // Cria Locale para português do Brasil
        Locale localUs = Locale.US; // Obtém Locale para Estados Unidos

        double valorMonetarioBruto = 1234.56; // Valor de exemplo para formatação de moeda
        NumberFormat formatoMoedaBr = NumberFormat.getCurrencyInstance(localPtBr); // Formata para moeda pt-BR
        NumberFormat formatoMoedaUs = NumberFormat.getCurrencyInstance(localUs); // Formata para moeda en-US

        System.out.println("=== Locale e Formatação ==="); // Imprime título
        System.out.println("pt-BR: " + formatoMoedaBr.format(valorMonetarioBruto)); // Exibe moeda pt-BR
        System.out.println("en-US: " + formatoMoedaUs.format(valorMonetarioBruto)); // Exibe moeda en-US
        System.out.println(); // Linha em branco
    }

    // =================== EXERCÍCIOS 1 a 5 ===================

    private static void exercicio1CelsiusParaFahrenheit(double tempCelsiusEntrada) { // Converte Celsius para Fahrenheit
        double resultadoFahrenheitSaida = tempCelsiusEntrada * 1.8 + 32.0; // Aplica fórmula F = C*1.8 + 32
        System.out.println("=== Exercício 1: Celsius -> Fahrenheit ==="); // Título
        System.out.println("Entrada (°C): " + tempCelsiusEntrada); // Mostra entrada
        System.out.println("Saída (°F): " + resultadoFahrenheitSaida); // Mostra saída
        System.out.println(); // Linha em branco
    }

    private static void exercicio2MaiorIdade(int idadePessoaEntrada) { // Verifica se é maior de idade
        System.out.println("=== Exercício 2: Maior Idade ==="); // Título
        if (idadePessoaEntrada >= 18) { // Condição de maioridade
            System.out.println("Idade " + idadePessoaEntrada + ": Você é maior de idade."); // Mensagem se >= 18
        } else { // Caso contrário
            System.out.println("Idade " + idadePessoaEntrada + ": Você NÃO é maior de idade."); // Mensagem se < 18
        }
        System.out.println(); // Linha em branco
    }

    private static void exercicio3ParOuImpar(int numeroInteiroEntrada) { // Verifica paridade
        System.out.println("=== Exercício 3: Par ou Ímpar ==="); // Título
        if (numeroInteiroEntrada % 2 == 0) { // Testa resto da divisão por 2
            System.out.println(numeroInteiroEntrada + " é PAR."); // Mensagem para par
        } else { // Caso contrário
            System.out.println(numeroInteiroEntrada + " é ÍMPAR."); // Mensagem para ímpar
        }
        System.out.println(); // Linha em branco
    }

    private static void exercicio4DiaDaSemana(int numeroDiaSemanaEntrada) { // Switch para dia
        System.out.println("=== Exercício 4: Dia da Semana ==="); // Título
        switch (numeroDiaSemanaEntrada) { // Abre switch
            case 1: // Caso 1
                System.out.println("Domingo"); // Imprime
                break; // Sai do switch
            case 2: // Caso 2
                System.out.println("Segunda-feira"); // Imprime
                break; // Sai do switch
            case 3: // Caso 3
                System.out.println("Terça-feira"); // Imprime
                break; // Sai do switch
            case 4: // Caso 4
                System.out.println("Quarta-feira"); // Imprime
                break; // Sai do switch
            case 5: // Caso 5
                System.out.println("Quinta-feira"); // Imprime
                break; // Sai do switch
            case 6: // Caso 6
                System.out.println("Sexta-feira"); // Imprime
                break; // Sai do switch
            case 7: // Caso 7
                System.out.println("Sábado"); // Imprime
                break; // Sai do switch
            default: // Qualquer outro número
                System.out.println("Dia inválido!"); // Mensagem de erro
                break; // Sai do switch
        }
        System.out.println(); // Linha em branco
    }

    private static void exercicio5CalculadoraSimples(double numeroOperandoA, double numeroOperandoB, char caractereOperador) { // Calculadora
        double resultadoOperacaoSaida; // Declara variável de resultado
        System.out.println("=== Exercício 5: Calculadora Simples ==="); // Título
        System.out.println("Entrada: " + numeroOperandoA + " " + caractereOperador + " " + numeroOperandoB); // Mostra operação
        switch (caractereOperador) { // Abre switch
            case '+': // Soma
                resultadoOperacaoSaida = numeroOperandoA + numeroOperandoB; // Calcula soma
                System.out.println("Resultado: " + resultadoOperacaoSaida); // Imprime
                break; // Sai do switch
            case '-': // Subtração
                resultadoOperacaoSaida = numeroOperandoA - numeroOperandoB; // Calcula subtração
                System.out.println("Resultado: " + resultadoOperacaoSaida); // Imprime
                break; // Sai
            case '*': // Multiplicação
                resultadoOperacaoSaida = numeroOperandoA * numeroOperandoB; // Calcula multiplicação
                System.out.println("Resultado: " + resultadoOperacaoSaida); // Imprime
                break; // Sai
            case '/': // Divisão
                if (numeroOperandoB != 0.0) { // Evita divisão por zero
                    resultadoOperacaoSaida = numeroOperandoA / numeroOperandoB; // Calcula divisão
                    System.out.println("Resultado: " + resultadoOperacaoSaida); // Imprime
                } else { // Se divisor zero
                    System.out.println("Erro: Divisão por zero não permitida!"); // Mensagem de erro
                }
                break; // Sai
            default: // Operador inválido
                System.out.println("Operador inválido!"); // Mensagem
                break; // Sai
        }
        System.out.println(); // Linha em branco
    }

    // =================== LAÇOS ===================

    private static void demoLacos() { // Demonstra laços
        System.out.println("=== Laços (for, while, do-while) ==="); // Título

        for (int contadorFor = 1; contadorFor <= 10; contadorFor++) { // For de 1 a 10
            System.out.print(contadorFor + (contadorFor < 10 ? ", " : "\n")); // Imprime na mesma linha
        }

        int contadorWhile = 1; // Inicializa contador do while
        while (contadorWhile <= 5) { // Condição do while
            System.out.print("W" + contadorWhile + (contadorWhile < 5 ? " " : "\n")); // Imprime marca
            contadorWhile++; // Incrementa
        }

        int contadorDoWhile = 1; // Inicializa contador do do-while
        do { // Inicia do-while
            System.out.print("D" + contadorDoWhile + (contadorDoWhile < 3 ? " " : "\n")); // Imprime
            contadorDoWhile++; // Incrementa
        } while (contadorDoWhile <= 3); // Condição de parada

        System.out.println(); // Linha em branco
    }

    // =================== STRINGS ===================

    private static void demoStrings() { // Demonstra operações com String
        String textoMensagemBase = "Grêmio é maior que aquele time"; // Cria texto base
        int comprimentoDoTexto = textoMensagemBase.length(); // Obtém o tamanho do texto
        String textoEmMaiusculas = textoMensagemBase.toUpperCase(); // Converte para maiúsculas
        String textoEmMinusculas = textoMensagemBase.toLowerCase(); // Converte para minúsculas
        boolean contemGremio = textoMensagemBase.contains("Grêmio"); // Verifica se contém substring
        String textoSubstituido = textoMensagemBase.replace("time", "grupo"); // Troca palavra
        String pedacoSubstring = textoMensagemBase.substring(0, 6); // Pega substring

        System.out.println("=== Strings ==="); // Título
        System.out.println("Texto: " + textoMensagemBase); // Exibe texto original
        System.out.println("Comprimento: " + comprimentoDoTexto); // Exibe tamanho
        System.out.println("Maiúsculas: " + textoEmMaiusculas); // Exibe maiúsculas
        System.out.println("Minúsculas: " + textoEmMinusculas); // Exibe minúsculas
        System.out.println("Contém 'Grêmio'? " + contemGremio); // Exibe contains
        System.out.println("Substituição: " + textoSubstituido); // Exibe replace
        System.out.println("Substring(0,6): " + pedacoSubstring); // Exibe substring
        System.out.println(); // Linha em branco
    }

    // =================== MATH ===================

    private static void demoFuncoesMatematicas() { // Demonstra funções Math
        double numeroOriginalParaMath = 50.30; // Número de exemplo
        double arredondadoMathRound = Math.round(numeroOriginalParaMath); // Arredonda para inteiro mais próximo
        double arredondadoParaCima = Math.ceil(numeroOriginalParaMath); // Arredonda para cima
        double arredondadoParaBaixo = Math.floor(numeroOriginalParaMath); // Arredonda para baixo
        double raizQuadrada = Math.sqrt(numeroOriginalParaMath); // Raiz quadrada
        double valorAbsoluto = Math.abs(-42.7); // Valor absoluto
        double aleatorioEntre0e1 = Math.random(); // Número aleatório [0,1)

        System.out.println("=== Funções Matemáticas ==="); // Título
        System.out.println("Número base: " + numeroOriginalParaMath); // Exibe número
        System.out.println("round: " + arredondadoMathRound); // Exibe round
        System.out.println("ceil: " + arredondadoParaCima); // Exibe ceil
        System.out.println("floor: " + arredondadoParaBaixo); // Exibe floor
        System.out.println("sqrt: " + raizQuadrada); // Exibe sqrt
        System.out.println("abs(-42.7): " + valorAbsoluto); // Exibe abs
        System.out.println("random: " + aleatorioEntre0e1); // Exibe random
        System.out.println(); // Linha em branco
    }

    // =================== ARRAYS 1D ===================

    private static void demoArrays1D() { // Demonstra arrays 1D
        int[] vetorExemplo = new int[10]; // Cria array de 10 posições inicializadas com 0
        vetorExemplo[0] = 1; // Atribui valor na posição 0
        vetorExemplo[1] = 2; // Atribui valor na posição 1
        vetorExemplo[2] = 3; // Atribui valor na posição 2
        vetorExemplo[3] = 4; // Atribui valor na posição 3
        vetorExemplo[4] = 5; // Atribui valor na posição 4
        vetorExemplo[5] = 6; // Atribui valor na posição 5
        vetorExemplo[6] = 7; // Atribui valor na posição 6
        vetorExemplo[7] = 8; // Atribui valor na posição 7
        vetorExemplo[8] = 9; // Atribui valor na posição 8
        vetorExemplo[9] = 10; // Atribui valor na posição 9

        System.out.println("=== Arrays 1D ==="); // Título
        System.out.println("Elementos do array:"); // Cabeçalho
        for (int indiceImpressao = 0; indiceImpressao < vetorExemplo.length; indiceImpressao++) { // Loop por índices
            System.out.print(vetorExemplo[indiceImpressao] + (indiceImpressao < vetorExemplo.length - 1 ? ", " : "\n")); // Imprime
        }

        int somaDoArray = 0; // Acumulador para soma
        for (int indiceSoma = 0; indiceSoma < vetorExemplo.length; indiceSoma++) { // Loop para somar
            somaDoArray += vetorExemplo[indiceSoma]; // Soma cada elemento
        }
        System.out.println("Soma: " + somaDoArray); // Exibe soma
        System.out.println(); // Linha em branco
    }

    // =================== EXERCÍCIO 6 ===================

    private static void exercicio6MaiorNumeroEmArray(int[] vetorEntrada) { // Encontra maior valor
        int valorMaximoEncontrado = vetorEntrada[0]; // Assume primeiro como maior
        for (int indiceComparacao = 1; indiceComparacao < vetorEntrada.length; indiceComparacao++) { // Percorre do 2º ao fim
            if (vetorEntrada[indiceComparacao] > valorMaximoEncontrado) { // Compara com atual máximo
                valorMaximoEncontrado = vetorEntrada[indiceComparacao]; // Atualiza máximo
            }
        }
        System.out.println("=== Exercício 6: Maior Número do Array ==="); // Título
        System.out.println("Array: " + Arrays.toString(vetorEntrada)); // Exibe array
        System.out.println("Maior valor: " + valorMaximoEncontrado); // Exibe resultado
        System.out.println(); // Linha em branco
    }

    // =================== EXERCÍCIO 7 ===================

    private static void exercicio7AdicionarElementoNoArray(int[] vetorOriginalEntrada, int novoElementoParaAdicionar) { // Adiciona elemento
        int tamanhoNovoArray = vetorOriginalEntrada.length + 1; // Calcula novo tamanho
        int[] vetorComElementoExtra = new int[tamanhoNovoArray]; // Cria novo array
        for (int indiceCopia = 0; indiceCopia < vetorOriginalEntrada.length; indiceCopia++) { // Copia elementos
            vetorComElementoExtra[indiceCopia] = vetorOriginalEntrada[indiceCopia]; // Copia posição a posição
        }
        vetorComElementoExtra[vetorComElementoExtra.length - 1] = novoElementoParaAdicionar; // Adiciona o novo ao final
        System.out.println("=== Exercício 7: Adicionar Elemento ==="); // Título
        System.out.println("Original: " + Arrays.toString(vetorOriginalEntrada)); // Exibe original
        System.out.println("Novo elemento: " + novoElementoParaAdicionar); // Exibe elemento
        System.out.println("Resultado: " + Arrays.toString(vetorComElementoExtra)); // Exibe resultado
        System.out.println(); // Linha em branco
    }

    // =================== MATRIZ 2D ===================

    private static void demoMatriz2D() { // Demonstra matriz 2D
        int[][] matrizExemplo2D = { // Declara e inicializa matriz 3x3
                {1, 2, 3}, // Primeira linha
                {4, 5, 6}, // Segunda linha
                {7, 8, 9}  // Terceira linha
        };

        System.out.println("=== Matriz 2D ==="); // Título
        for (int indiceLinha = 0; indiceLinha < matrizExemplo2D.length; indiceLinha++) { // Itera linhas
            for (int indiceColuna = 0; indiceColuna < matrizExemplo2D[indiceLinha].length; indiceColuna++) { // Itera colunas
                System.out.print(matrizExemplo2D[indiceLinha][indiceColuna] + " "); // Imprime elemento
            }
            System.out.println(); // Quebra de linha após cada linha
        }
        System.out.println(); // Linha em branco
    }

    // =================== EXERCÍCIO 8 ===================

    private static void exercicio8SomaDasDiagonais(int[][] matrizQuadradaEntrada) { // Soma diagonais principal e secundária
        int somaDiagonalPrincipal = 0; // Acumulador para diagonal principal
        int somaDiagonalSecundaria = 0; // Acumulador para diagonal secundária
        int tamanhoMatrizQuadrada = matrizQuadradaEntrada.length; // Tamanho N da matriz NxN

        for (int indiceDiagonal = 0; indiceDiagonal < tamanhoMatrizQuadrada; indiceDiagonal++) { // Percorre índices 0..N-1
            somaDiagonalPrincipal += matrizQuadradaEntrada[indiceDiagonal][indiceDiagonal]; // Soma elementos (i,i)
            somaDiagonalSecundaria += matrizQuadradaEntrada[indiceDiagonal][tamanhoMatrizQuadrada - 1 - indiceDiagonal]; // Soma (i,N-1-i)
        }

        System.out.println("=== Exercício 8: Soma das Diagonais ==="); // Título
        System.out.println("Matriz: " + Arrays.deepToString(matrizQuadradaEntrada)); // Exibe matriz
        System.out.println("Diagonal principal: " + somaDiagonalPrincipal); // Exibe soma principal
        System.out.println("Diagonal secundária: " + somaDiagonalSecundaria); // Exibe soma secundária
        System.out.println("Soma total (se centro contado duas vezes em N ímpar): " + (somaDiagonalPrincipal + somaDiagonalSecundaria)); // Observação
        System.out.println(); // Linha em branco
    }

    // =================== BIGDECIMAL (FINANCEIRO) ===================

    private static void demoBigDecimalFinanceiro() { // Demonstra cálculo monetário com BigDecimal
        BigDecimal precoUnitarioProduto = new BigDecimal("19.99"); // Preço unitário em BigDecimal (string evita erro de binário)
        BigDecimal quantidadeVendida = new BigDecimal("3"); // Quantidade vendida
        BigDecimal subtotalCalculado = precoUnitarioProduto.multiply(quantidadeVendida); // Subtotal = preço * quantidade

        BigDecimal aliquotaImposto = new BigDecimal("0.085"); // Alíquota de 8.5%
        BigDecimal valorImpostoCalculado = subtotalCalculado.multiply(aliquotaImposto); // Imposto = subtotal * alíquota

        BigDecimal totalComImposto = subtotalCalculado.add(valorImpostoCalculado); // Total = subtotal + imposto

        NumberFormat formatoMoedaPadrao = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")); // Formato moeda BR

        System.out.println("=== BigDecimal (Financeiro) ==="); // Título
        System.out.println("Preço unitário: " + formatoMoedaPadrao.format(precoUnitarioProduto)); // Exibe preço
        System.out.println("Quantidade: " + quantidadeVendida); // Exibe quantidade
        System.out.println("Subtotal: " + formatoMoedaPadrao.format(subtotalCalculado)); // Exibe subtotal
        System.out.println("Imposto (8,5%): " + formatoMoedaPadrao.format(valorImpostoCalculado)); // Exibe imposto
        System.out.println("Total: " + formatoMoedaPadrao.format(totalComImposto)); // Exibe total
        System.out.println(); // Linha em branco
    }

    // =================== ORDENAR E BUSCAR EM ARRAYS ===================

    private static void demoOrdenacaoEBusca() { // Demonstra sort e binarySearch
        int[] numerosParaOrdenar = {42, 7, 19, 3, 7, 88, 1}; // Array desordenado com duplicatas
        System.out.println("=== Ordenação e Busca ==="); // Título
        System.out.println("Original: " + Arrays.toString(numerosParaOrdenar)); // Exibe original
        Arrays.sort(numerosParaOrdenar); // Ordena em ordem crescente
        System.out.println("Ordenado: " + Arrays.toString(numerosParaOrdenar)); // Exibe ordenado
        int indiceBuscaDo19 = Arrays.binarySearch(numerosParaOrdenar, 19); // Busca binária do 19
        System.out.println("Posição do 19 (se >=0, encontrado): " + indiceBuscaDo19); // Exibe resultado

        System.out.print("For-each: "); // Cabeçalho para for-each
        for (int valorItem : numerosParaOrdenar) { // Percorre com for-each
            System.out.print(valorItem + " "); // Imprime cada valor
        }
        System.out.println(); // Quebra de linha
        System.out.println(); // Linha em branco
    }

    // =================== COLLECTIONS (LIST) ===================

    private static void demoCollectionsList() { // Demonstra List com ArrayList
        List<String> listaDeNomes = new ArrayList<>(); // Cria lista dinâmica de Strings
        listaDeNomes.add("Ana"); // Adiciona elemento
        listaDeNomes.add("Bruno"); // Adiciona elemento
        listaDeNomes.add("Carla"); // Adiciona elemento

        System.out.println("=== Collections (List) ==="); // Título
        System.out.println("Lista inicial: " + listaDeNomes); // Exibe lista

        listaDeNomes.remove("Bruno"); // Remove por valor
        listaDeNomes.add(1, "Bianca"); // Insere em índice específico

        System.out.println("Após alterações: " + listaDeNomes); // Exibe alterações
        System.out.println("Contém 'Ana'? " + listaDeNomes.contains("Ana")); // Verifica presença
        System.out.println("Tamanho: " + listaDeNomes.size()); // Tamanho da lista

        for (int indiceLista = 0; indiceLista < listaDeNomes.size(); indiceLista++) { // Percorre com índice
            System.out.println("Posição " + indiceLista + ": " + listaDeNomes.get(indiceLista)); // Exibe item
        }
        System.out.println(); // Linha em branco
    }

    // =================== FUNÇÕES UTILITÁRIAS ===================

    private static void demoFuncoesUtilitarias() { // Demonstra funções extras
        String palavraParaReverter = "radar"; // Palavra que é palíndromo
        String reversoDaPalavra = reverterString(palavraParaReverter); // Reverte a palavra
        boolean ehPalindromoResultado = ehPalindromo(palavraParaReverter); // Verifica palíndromo

        System.out.println("=== Funções Utilitárias ==="); // Título
        System.out.println("Original: " + palavraParaReverter); // Exibe original
        System.out.println("Reverso: " + reversoDaPalavra); // Exibe reverso
        System.out.println("É palíndromo? " + ehPalindromoResultado); // Exibe veredito
        System.out.println(); // Linha em branco
    }

    private static String reverterString(String textoParaReverterEntrada) { // Reverte String
        StringBuilder construtorReverso = new StringBuilder(textoParaReverterEntrada); // Usa StringBuilder
        return construtorReverso.reverse().toString(); // Retorna reverso como String
    }

    private static boolean ehPalindromo(String textoParaVerificacaoEntrada) { // Verifica palíndromo
        String textoNormalizado = textoParaVerificacaoEntrada.replaceAll("\\s+", "").toLowerCase(); // Remove espaços e normaliza
        String textoInvertido = new StringBuilder(textoNormalizado).reverse().toString(); // Inverte o normalizado
        return textoNormalizado.equals(textoInvertido); // Compara normalizado com invertido
    }

    // =================== SCANNER ===================

    private static void demoScanner() { // Demonstra leitura com Scanner
        Scanner leitorEntradaTeclado = new Scanner(System.in); // Cria Scanner que lê do teclado
        System.out.print("Digite um número inteiro: "); // Solicita número
        int inteiroLidoDoUsuario = leitorEntradaTeclado.nextInt(); // Lê inteiro
        System.out.println("Você digitou: " + inteiroLidoDoUsuario); // Mostra o que foi digitado
        leitorEntradaTeclado.close(); // Fecha o Scanner (boa prática)
        System.out.println(); // Linha em branco
    }
}
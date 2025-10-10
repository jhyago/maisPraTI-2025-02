package ExercicioRelacionamentoDeObjetos;

public class SistemaGerenciamento {
    public static void main(String[] args) {
        Veiculo carro = new Carro("Chevrolet", "Vectra", 1999);
        Veiculo moto = new Moto("Yamaha", "R1", 2020);

        Proprietario proprietario = new Proprietario("Juan", "Rua do Juan");

        proprietario.adicionarVeiculo(carro);
        proprietario.adicionarVeiculo(moto);

        proprietario.exibirVeiculos();

        Servico servico1 = new Servico("Troca de óleo", 150.0);
        Servico servico2 = new Servico("Troca de pivô", 200.0);

        Oficina oficina = new Oficina();

        proprietario.comprarServico(oficina, servico1);

        oficina.listarServicos();
    }
}

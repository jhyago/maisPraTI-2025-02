package ExercicioRelacionamentoDeObjetos;

import java.util.ArrayList;
import java.util.List;

public class Proprietario {
    private String nome;
    private String endereco;
    private List<Veiculo> veiculos;

    public Proprietario(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.veiculos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public void exibirVeiculos() {
        System.out.println("Proprietário: " + this.nome);
        System.out.println("Endereco: " + this.endereco);
        for (Veiculo veiculo : this.veiculos) {
            veiculo.exibirInformacoes();
            veiculo.tipoVeiculo();
            System.out.println();
        }
    }

    public void comprarServico(Oficina oficina, Servico servico) {
        oficina.realizarServico(servico);
        System.out.println(this.nome + " comprou o serviço " + servico.getDescricao());
    }
}

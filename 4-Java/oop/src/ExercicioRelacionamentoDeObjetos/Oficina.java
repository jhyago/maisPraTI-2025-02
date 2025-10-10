package ExercicioRelacionamentoDeObjetos;

import java.util.ArrayList;
import java.util.List;

public class Oficina {
    private List<Servico> servicosRealizados;

    public Oficina() {
        this.servicosRealizados = new ArrayList<>();
    }

    public void realizarServico(Servico servico) {
        servicosRealizados.add(servico);
    }

    public void listarServicos() {
        for (Servico servico : this.servicosRealizados) {
            servico.exibirServico();
            System.out.println();
        }
    }
}

package ExercicioRelacionamentoDeObjetos;

public class Carro extends Veiculo {
    public Carro(String marca, String modelo, int ano) {
        super(marca, modelo, ano);
    }

    @Override
    public void tipoVeiculo() {
        System.out.println("Tipo: Carro");
    }
}

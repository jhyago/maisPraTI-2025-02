package exercicio4;

public class Carro implements IMeioTransporte{
    private int velocidade;
    private final int limiteVelocidade;

    public Carro(){
        this.velocidade = 0;
        this.limiteVelocidade = 180;
    }

    @Override
    public void acelerar() {
        if(this.velocidade < this.limiteVelocidade){
            this.velocidade += 10;
            System.out.println("Carro acelerando, velocidade atual: " + this.velocidade);
        } else {
            throw new IllegalStateException("Carro atingiu o limite de velocidade!");
        }
    }

    @Override
    public void frear() {
        if(this.velocidade > 0) {
            this.velocidade -= 10;
            System.out.println("Carro freando, velocidade atual: " + this.velocidade);
        }
    }
}

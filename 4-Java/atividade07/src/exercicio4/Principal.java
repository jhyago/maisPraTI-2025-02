package exercicio4;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        List<IMeioTransporte> meiosDeTransporte = new ArrayList<>();

        meiosDeTransporte.add(new Carro());

        for (IMeioTransporte meioTransporte : meiosDeTransporte) {
            try {
                meioTransporte.acelerar();
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

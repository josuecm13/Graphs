package game.logic.escudos;

import game.logic.Estructuras.Nodo;
import game.logic.ataque.Ataque;

public class Espejo extends IEscudo{

    public Espejo(Nodo comprador){
        this.comprador = comprador;
    }


    @Override
    public void proteger(int herida, Ataque ataque) {
        comprador.setSalud(comprador.getSalud() + herida);
        comprador.getMensaje(0).contraatacar(ataque.promedio,ataque.recorrido);
    }
}

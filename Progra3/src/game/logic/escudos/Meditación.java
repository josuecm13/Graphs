package game.logic.escudos;

import game.logic.Nodo;
import game.logic.ataque.Ataque;

public class Meditación extends IEscudo{

    
    public Meditación(Nodo comprador) {
        this.comprador = comprador;
    }

    @Override
    public void proteger(int herida, Ataque ataque) {

    }
}

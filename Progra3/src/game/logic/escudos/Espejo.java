package game.logic.escudos;

import game.logic.Arista;
import game.logic.Nodo;
import game.logic.ataque.Ataque;

public class Espejo extends IEscudo{

    public Espejo(Nodo dueño){
        this.dueño = dueño;
    }


    @Override
    void proteger(int daño, Ataque ataque) {

    }
}

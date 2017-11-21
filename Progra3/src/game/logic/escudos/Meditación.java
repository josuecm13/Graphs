package game.logic.escudos;

import game.logic.Nodo;
import game.logic.ataque.Ataque;

public class Meditación extends IEscudo{



    public Meditación(Nodo dueño) {
        this.dueño = dueño;
    }

    @Override
    void proteger(int daño, Ataque ataque) {

    }
}

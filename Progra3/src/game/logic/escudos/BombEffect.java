package game.logic.escudos;

import game.logic.Arista;
import game.logic.Nodo;
import game.logic.ataque.Ataque;

public class BombEffect extends IEscudo {

    public BombEffect(Nodo dueño){
        this.dueño = dueño;
    }

    @Override
    void proteger(int daño, Ataque ataque) {
        for(Arista a: dueño.aritas){
            dueño.atacar(a);
        }
    }
}

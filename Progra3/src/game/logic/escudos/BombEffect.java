package game.logic.escudos;

import game.logic.Arista;
import game.logic.Nodo;
import game.logic.ataque.Ataque;

public class BombEffect extends IEscudo {

    public BombEffect(Nodo dueño){
        this.comprador = dueño;
    }

    @Override
    public void proteger(int herida, Ataque ataque) {
        for(Arista a: comprador.aritas){
            comprador.atacar(a,ataque.promedio);
        }
    }
}

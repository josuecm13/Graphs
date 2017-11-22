package game.logic.escudos;

import game.logic.Estructuras.Nodo;
import game.logic.ataque.Ataque;

public abstract class IEscudo {

    Nodo comprador;

    public abstract void proteger(int herida, Ataque ataque);

}

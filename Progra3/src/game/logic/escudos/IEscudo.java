package game.logic.escudos;

import game.logic.Nodo;
import game.logic.ataque.Ataque;

public abstract class IEscudo {

    Nodo dueño;

    public abstract void proteger(int daño, Ataque ataque);

}

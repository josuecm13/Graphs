package game.logic.escudos;

import game.logic.Nodo;

public abstract class IEscudo {

    Nodo dueño;

    abstract void proteger( int daño);

}

package game.logic.ataque;

import game.logic.Estructuras.Nodo;

import java.util.List;

public interface IAtaque {
    void atacar(int promedioGrafo, List<Nodo> recorrido);
}

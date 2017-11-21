package game.logic.ataque;

import game.logic.Nodo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class Ataque implements IAtaque {

    Nodo origen;
    List<Nodo> recorrido;

    Ataque(Nodo origen){
        this.origen = origen;
        recorrido = new ArrayList<>();
    }

    @Override
    public void atacar(int promedioGrafo, List<Nodo> recorrido) {
        throw new NotImplementedException();
    }

    public Nodo getOrigen(){
        return origen;
    }
}

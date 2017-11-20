package game.logic.ataque;

import game.logic.Nodo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Ataque implements IAtaque {

    Nodo origen;


    Ataque(Nodo origen){
        this.origen = origen;
    }


    @Override
    public void atacar(int promedioGrafo) {
        throw new NotImplementedException();
    }
}

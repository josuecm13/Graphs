package game.logic.ataque;

import game.logic.Estructuras.Nodo;

import java.util.List;

public class Prim extends Ataque {
    Prim(Nodo origen) {
        super(origen);
    }

    @Override
    public void atacar(int promedioGrafo, List<Nodo> recorrido){
        int randint = random.nextInt(3);
        promedio = promedioGrafo;
        int herida = (int) (randint * (0.6* promedioGrafo));
        atacar(recorrido,herida,DEFAULT);
    }
}

package game.logic.ataque;

import game.logic.Nodo;

import java.util.List;

public class Kruscal extends Ataque {
    Kruscal(Nodo origen) {
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

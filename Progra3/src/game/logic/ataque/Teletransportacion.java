package game.logic.ataque;

import game.logic.Estructuras.Nodo;

import java.util.List;

public class Teletransportacion extends Ataque {
    Teletransportacion(Nodo origen) {
        super(origen);
    }

    @Override
    public void atacar(int promedioGrafo, List<Nodo> recorrido){
        int randint = random.nextInt(2)+3;
        promedio = promedioGrafo;
        int herida = (randint * (10* promedioGrafo));
        atacar(recorrido,herida,FASTWAY);
    }
}

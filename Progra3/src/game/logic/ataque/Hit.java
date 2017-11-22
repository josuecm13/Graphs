package game.logic.ataque;

import game.logic.Estructuras.Nodo;

import java.util.List;

public class Hit extends Ataque {

    public Hit(Nodo origen) {
        super(origen);
    }

    @Override
    public void atacar(int promedioGrafo, List<Nodo> recorrido) {
        int randint = random.nextInt(2)+3;
        promedio = promedioGrafo;
        int herida = (int) (randint * (0.4* promedioGrafo));
        atacar(recorrido,herida,DEFAULT);
    }


    @Override
    public void contraatacar(int promedioGrafo, List<Nodo> recorrido) {
        int randint = random.nextInt(2)+3;
        promedio = promedioGrafo;
        int herida = (int) (randint * (0.4* promedioGrafo));
        atacar(recorrido,herida,CONTRAATAQUE);
    }
}

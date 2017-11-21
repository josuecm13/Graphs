package game.logic.ataque;

import game.logic.Nodo;

import java.util.List;

public class Kamikaze extends Ataque{

    public Kamikaze(Nodo origen) {
        super(origen);
    }

    @Override
    public void atacar(int promedioGrafo, List<Nodo> recorrido) {
        int randint = random.nextInt(2)+3;
        promedio = promedioGrafo;
        int herida = (int) (randint * ((10/recorrido.size())* promedioGrafo));
        atacar(recorrido,herida,KAMIKAZE);
    }

    @Override
    public void atacaMultiple(int promedioGrafo, List<List<Nodo>> recorrido) {
        for (List<Nodo> lista: recorrido) {
            atacar(promedioGrafo,lista);
        }
    }
}

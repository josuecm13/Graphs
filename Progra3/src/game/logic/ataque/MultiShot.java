package game.logic.ataque;

import game.logic.Estructuras.Nodo;

import java.util.List;

public class MultiShot extends Ataque {
    MultiShot(Nodo origen) {
        super(origen);
    }

    @Override
    public void atacar(int promedioGrafo, List<Nodo> recorrido){
        int randint = random.nextInt(2)+3;
        promedio = promedioGrafo;
        int herida = (randint * ((10/recorrido.size())* promedioGrafo));
        atacar(recorrido,herida,DEFAULT);
    }

    @Override
    public void atacaMultiple(int promedioGrafo, List<List<Nodo>> recorrido) {
        for (List<Nodo> lista: recorrido) {
            atacar(promedioGrafo,lista);
        }
    }

}

package game.logic.ataque;

import game.logic.Arista;
import game.logic.Nodo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hit extends Ataque {

    public Hit(Nodo origen) {
        super(origen);
    }

    @Override
    public void atacar(int promedioGrafo, List<Nodo> recorrido) {
        Random random = new Random();
        int randint = random.nextInt(2)+3;
        int herida = (int) (randint * (0.4* promedioGrafo));
        List<Arista> aristas = new ArrayList<>();
        int i;
        for(i = 0; i < recorrido.size() - 1; i++){
            Nodo a = recorrido.get(i);
            aristas.add(a.buscarArista(recorrido.get(i+1)));
        }
        for(Arista a: aristas){
            herida -= a.getPeso();
            a.disminuirVida(10);
        }
        recorrido.get(i).recibirAtaque(this,herida);
    }

}

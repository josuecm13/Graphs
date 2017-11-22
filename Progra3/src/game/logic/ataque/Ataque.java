package game.logic.ataque;

import game.logic.Estructuras.Arista;
import game.logic.Estructuras.Nodo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ataque implements IAtaque {

    Nodo origen;
    Random random = new Random();
    public List<Nodo> recorrido;
    final int FASTWAY = -40;
    final int DEFAULT = 0;
    final int KAMIKAZE = Integer.MAX_VALUE;
    final int CONTRAATAQUE = 24;
    public int promedio;

    Ataque(Nodo origen){
        this.origen = origen;
        recorrido = new ArrayList<>();
        promedio = 1;
    }

    @Override
    public void atacar(int promedioGrafo, List<Nodo> recorrido) {
        throw new NotImplementedException();
    }

    protected void atacar(List<Nodo> recorrido, int herida, int opcion ){
        this.recorrido = recorrido;
        int heridaOriginal = herida;
        List<Arista> aristas = new ArrayList<>();
        int i;
        switch (opcion){
            case FASTWAY:
                for(i = 0; i < recorrido.size() - 1; i++){
                    Nodo a = recorrido.get(i);
                    aristas.add(a.buscarArista(recorrido.get(i+1)));
                }
                for(Arista a: aristas){
                    a.disminuirVida(10);
                    herida -= a.getFastWay();
                }
                herida = herida < 0 ? 0: herida;
                recorrido.get(i).recibirAtaque(this,herida/heridaOriginal);
                break;
            case CONTRAATAQUE:
                for(i = recorrido.size() - 1; i > 0; i--){
                    Nodo a = recorrido.get(i);
                    aristas.add(a.buscarArista(recorrido.get(i-1)));
                }
                for(Arista a: aristas) {
                    a.disminuirVida(10);
                    herida += a.getPeso();
                }
                recorrido.get(i).recibirAtaque(this,herida/heridaOriginal);
                break;
            default:
                for(i = 0; i < recorrido.size() - 1; i++){
                    Nodo a = recorrido.get(i);
                    aristas.add(a.buscarArista(recorrido.get(i+1)));
                }
                for(Arista a: aristas) {
                    a.disminuirVida(10);
                    herida -= a.getPeso();
                }
                herida = herida < 0 ? 0: herida;
                recorrido.get(i).recibirAtaque(this,herida/heridaOriginal);
                break;
        }
    }

    public void contraatacar(int promedioGrafo, List<Nodo> recorrido){
        throw new NotImplementedException();
    }


    public void atacaMultiple(int promedioGrafo, List<List<Nodo>> recorrido){
        throw new NotImplementedException();
    }

    public Nodo getOrigen(){
        return origen;
    }
}

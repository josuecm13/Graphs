package game.logic.ataque;

import game.logic.Nodo;

public class FabricaAtaque {

    public static int CANTIDAD_DE_ATAQUES = 6;

    public static Ataque getInstance(int i, Nodo origen){
        switch (i){
            case 0:
                return new Hit(origen);
            case 1:
                return new Prim(origen);
            case 2:
                return new Kruscal(origen);
            case 3:
                return new Teletransportacion(origen);
            case 4:
                return new MultiShot(origen);
            default:
                return new Kamikaze(origen);
        }
    }

}

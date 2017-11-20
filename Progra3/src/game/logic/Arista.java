package game.logic;

public class Arista {

    private int peso;
    private Nodo destino;
    private int fastWay;

    public Arista(int peso) {
        this.peso = peso;
        fastWay = (int) (peso + peso*0.60 + 12);
    }

    public Arista(int peso, int fastWay){
        this.fastWay = fastWay;
        this.peso = peso;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    public void disminuirVida(int cant){
        this.peso = peso - cant < 0 ? 0: (peso - cant);
    }

    public Nodo getDestino() {
        return destino;
    }
}

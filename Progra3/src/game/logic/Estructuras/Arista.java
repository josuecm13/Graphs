package game.logic.Estructuras;

public class Arista {

    private int peso;
    private int pesoOriginal;
    private Nodo destino;
    private int fastWay;
    boolean activo;
    private Nodo origen;
    private RecuperadorArista recuperacion;
    private GrafoMatriz matriz;

    Arista(int peso) {
        this.peso = peso;
        pesoOriginal = peso;
        fastWay = (int) (peso + peso*0.60 + 12);
        turnOn();
        recuperacion = new RecuperadorArista(this);
        Thread thread = new Thread(recuperacion);
        thread.run();
    }

    Arista(int peso, GrafoMatriz matriz, Nodo origen) {
        this.peso = peso;
        pesoOriginal = peso;
        fastWay = (int) (peso + peso * 0.60 + 12);
        recuperacion = new RecuperadorArista(this);
        Thread thread = new Thread(recuperacion);
        thread.run();
        this.matriz = matriz;
        this.origen = origen;
    }

    public Arista(int peso, int fastWay){
        this.fastWay = fastWay;
        this.peso = peso;
    }

    void setDestino(Nodo destino) {
        this.destino = destino;
    }

    public void disminuirVida(int cant){
        if(peso-cant < 0){
            peso = 0;
            turnOff();
            recuperacion.activar();
        }else{
            peso -= cant;
        }
    }

    Nodo getDestino() {
        return destino;
    }

    public int getPeso(){
        return peso;
    }

    public int getFastWay(){
        return fastWay;
    }

    private void turnOff(){
        activo = false;
        matriz.desactivarArista(origen.getId(),destino.getId());
    }

    void turnOn(){
        activo = true;
        try {
            matriz.activarArista(origen.getId(),destino.getId());
        }catch (Exception ignored){}
    }

    void setSegundosRecuperacion(int i){
        recuperacion.setSegundo(i);
    }

    private void restaurarVida(){
        peso = pesoOriginal;
    }

    private class RecuperadorArista implements Runnable {

        private Arista arista;
        private int segundo;
        private boolean activo;

        RecuperadorArista(Arista arista) {
            this.arista = arista;
            activo = false;
        }

        @Override
        public void run() {
            while (activo){
                try {
                    Thread.sleep(segundo*1000);
                    arista.restaurarVida();
                    activo =false;
                }catch (InterruptedException ignored){

                }
            }
        }

        public void setSegundo(int segundo){
            this.segundo = segundo;
        }

        void activar(){
            this.activo = true;
        }

    }

}

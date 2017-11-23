package game.logic.escudos;

import game.logic.Estructuras.Nodo;
import game.logic.ataque.Ataque;

public class Meditacion extends IEscudo{

    private MeditationThread recuperador;

    public Meditacion(Nodo comprador) {
        this.comprador = comprador;
        recuperador = new MeditationThread(2,comprador);
        Thread thread = new Thread(recuperador);
        thread.run();
    }

    @Override
    public void proteger(int herida, Ataque ataque) {
        recuperador.activar();
    }

    private class MeditationThread implements Runnable{

        private int minRecuperacion;
        private Nodo comprador;
        private boolean activo = false;

        MeditationThread(int minutos,Nodo owner){
            minRecuperacion = minutos;
            comprador = owner;
        }

        @Override
        public void run() {
            while(activo){
                try{
                    Thread.sleep(minRecuperacion*60000);
                    comprador.regenerar();
                    activo = false;
                }catch (InterruptedException ignored){

                }
            }
        }

        public void setMinRecuperacion(int minRecuperacion) {
            this.minRecuperacion = minRecuperacion;
        }

        void activar(){
            this.activo = true;
        }

    }

}

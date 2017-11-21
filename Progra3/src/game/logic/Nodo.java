package game.logic;

import game.logic.ataque.Ataque;
import game.logic.ataque.FabricaAtaque;
import game.logic.ataque.Hit;
import game.logic.escudos.IEscudo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Nodo {

    private final String id;
    private int dineroInicial;
    private int dineroAcumulador;
    private double salud;
    private boolean turno;
    public int totalPeso;
    public boolean visitado = false;
    private List<IEscudo> escudos;
    public List<Arista> aritas;
    private List<Nodo> hijos;
    private List<Ataque> mensajes;

    public Nodo(String id){
        this.id = id;
        salud = 100;
        turno = false;
        aritas = new ArrayList<>();
        hijos = new ArrayList<>();
        totalPeso = 0;
        escudos = new ArrayList<>();
        mensajes = new ArrayList<>();
        llenarMensajes();
    }


    private void llenarMensajes(){
        for (int i = 0; i < FabricaAtaque.CANTIDAD_DE_ATAQUES ; i++) {
            mensajes.add(FabricaAtaque.getInstance(i,this));
        }
    }

    public boolean containsA(Nodo nodo){
        for (Arista a: aritas){
            if(a.getDestino() == nodo){
                return true;
            }
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void agregarArista(Arista a){
        aritas.add(a);
        totalPeso += a.getPeso();
    }

    public void borrarArista(String destino){
        for(Arista a : aritas){
            if(Objects.equals(a.getDestino().getId(), destino)){
                aritas.remove(a);
                totalPeso -= a.getPeso();
            }
        }
    }

    public void imprimir(){
        for(Arista a: aritas){
            System.out.print(a.getDestino().getId() + "\t");
        }
    }


    public void atacar(Arista a){
        a.disminuirVida(12);
    }


    public Arista buscarArista(Nodo destino){
        for (Arista g: aritas) {
            if(g.getDestino() == destino)
                return g;
        }
        return null;
    }

    public void recibirAtaque(Ataque ataque, int herida){
        this.salud = salud - herida < 0 ? 0: (salud - herida);
        for(IEscudo escudo: escudos){
            escudo.proteger(herida,ataque);
        }
        if(salud == 0){
            Nodo padre = ataque.getOrigen();
            for(Nodo e: hijos){
               padre.agregarHijo(e);
            }
            padre.agregarHijo(this);
        }
    }

    private void agregarHijo(Nodo hijo){
        hijos.add(hijo);
    }

    public List<Ataque> getMensajes() {
        return mensajes;
    }
}

package game.logic.Estructuras;

import game.logic.ataque.Ataque;
import game.logic.ataque.FabricaAtaque;
import game.logic.escudos.IEscudo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Nodo {

    private final String id;
    private int dineroInicial;
    private int dineroAcumulado;
    private double salud;
    private boolean turno;
    public int totalPeso;
    public boolean visitado = false;
    private List<IEscudo> escudos;
    public List<Arista> aritas;
    private List<Nodo> hijos;
    private List<Ataque> mensajes;
    private int minMeditacion;

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

    public Nodo(String id,int dineroInicial){
        this.id = id;
        salud = 100;
        turno = false;
        aritas = new ArrayList<>();
        hijos = new ArrayList<>();
        totalPeso = 0;
        escudos = new ArrayList<>();
        mensajes = new ArrayList<>();
        llenarMensajes();
        this.dineroInicial = this.dineroAcumulado = dineroInicial;
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

    public void atacar(Arista a, int promedio){
        a.disminuirVida(10);
        List<Nodo> recorrido = new ArrayList<>();
        recorrido.add(this);
        recorrido.add(a.getDestino());
        mensajes.get(0).atacar(promedio,recorrido);
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
        escudos = new ArrayList<>();
        if(salud == 0){
            Nodo padre = ataque.getOrigen();
            for(Nodo e: hijos){
               padre.agregarHijo(e);
               padre.vaciar(e);
            }
            padre.vaciar(this);
            padre.agregarHijo(this);
        }
    }

    private void agregarHijo(Nodo hijo){
        hijos.add(hijo);
    }

    public void regenerar() {
        salud += salud * 0.2;
    }

    public Ataque getMensaje(int i) {
        return mensajes.get(i);
    }

    void setSegundosArista(int segundosAristas){
        for(Arista i : aritas){
            i.setSegundosRecuperacion(segundosAristas);
        }
    }

    public void setMinMeditacion(int minMeditacion) {
        this.minMeditacion = minMeditacion;
    }

    public void setMeditacion(int min){
        minMeditacion = min;
    }

    public double getSalud() {
        return salud;
    }

    public boolean compraValida(int cant){
        this.dineroAcumulado = dineroAcumulado - cant > 0
                ? dineroAcumulado - cant : 0;
        return dineroAcumulado - cant > 0;
    }

    private void vaciar(Nodo hijo){
        this.dineroAcumulado += hijo.dineroAcumulado  ;
        hijo.dineroAcumulado = 0;
    }

    public void setSalud(double salud) {
        this.salud = salud;
    }
}

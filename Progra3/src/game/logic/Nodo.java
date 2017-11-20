package game.logic;

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
    public List<Arista> aritas;
    private List<Nodo> hijos;

    public Nodo(String id){
        this.id = id;
        salud = 100;
        turno = false;
        aritas = new ArrayList<>();
        hijos = new ArrayList<>();
        totalPeso = 0;
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
    }

    public void borrarArista(String destino){
        for(Arista a : aritas){
            if(Objects.equals(a.getDestino().getId(), destino)){
                aritas.remove(a);
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






}

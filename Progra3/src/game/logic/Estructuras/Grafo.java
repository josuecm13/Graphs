package game.logic.Estructuras;


import game.logic.exceptions.AlreadyInsertedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grafo {

    public GrafoMatriz matrices;
    List<Nodo> vertices;
    List<Nodo> pila;
    List<String> usernames;
    Nodo jugador;
    public static int KAMIKAZE = 64;
    public static int DEFAULT = 0;
    private int recuperacionArista = 60;
    private int[] configuraciones = {750,2,100000,60,2500};

    public Grafo(){
        vertices = new ArrayList<>();
        pila = new ArrayList<>();
        usernames = new ArrayList<>();
        matrices = new GrafoMatriz();
    }


    public void insertarVertice(String id) throws AlreadyInsertedException{
        if(usernames.indexOf(id) != -1){
            throw new AlreadyInsertedException();
        }
        Nodo nodo = new Nodo(id);
        vertices.add(nodo);
        usernames.add(id);
        matrices.agregarVertice(id);
    }

    public void insertarArista(String or, String dest, int peso) throws AlreadyInsertedException{
        Nodo origen = buscarNodo(or);
        Nodo destino = buscarNodo(dest);
        assert origen != null && destino != null;
        if(origen.containsA(destino)){
            throw new AlreadyInsertedException();
        }
        Arista AtoB = new Arista(peso,matrices,origen);
        AtoB.setDestino(destino);
        AtoB.turnOn();
        origen.agregarArista(AtoB);
        origen.totalPeso += peso;
        matrices.agregarArista(or,dest,peso);
    }

    public Nodo buscarNodo(String id){
        for(Nodo i : vertices){
            if(Objects.equals(i.getId(), id)){
                return i;
            }
        }
        return null;
    }

    private void borrarArista(String origen, String destino){
        Nodo or = buscarNodo(origen);
        assert or != null;
        or.borrarArista(destino);
    }

    public void borrarVertice(String dato){
        for (Nodo i : vertices){
            borrarArista(i.getId(),dato);
        }
        vertices.remove(buscarNodo(dato));
        usernames.remove(dato);
    }

    void visitarVertice(String dato){
        Nodo tmp = buscarNodo(dato);
        if (tmp != null)
            tmp.visitado = true;
    }

    boolean visitadoVertice(String dato) {
        Nodo tmp = buscarNodo(dato);
        return tmp != null && tmp.visitado;
    }

    void limpiarVisitados(){
        for (Nodo e : vertices){
            e.visitado = false;
        }
    }

    public int calcularPeso(){
        int i = 0;
        int result = 0;
        for (Nodo v: vertices) {
            result += v.totalPeso;
            i++;
        }
        return result/i;
    }

    public void imprimir(){
        matrices.imprimir();
    }

    public ArrayList<Nodo> profundidad(Nodo origen, String destino){
        ArrayList<String> lista = matrices.profundidad(vertices.indexOf(origen),destino,new ArrayList<>());
        ArrayList<Nodo> result = new ArrayList<>();
        for(String e : lista){
            result.add(buscarNodo(e));
        }
        return result.get(vertices.size() - 1) == buscarNodo(destino) ? result: null;
    }

    public ArrayList<ArrayList<Nodo>> multishot(Nodo origen, String destino, int tipo){
        ArrayList<ArrayList<Nodo>> lists = new ArrayList<>();
        Nodo dest = buscarNodo(destino);
        if(tipo == Grafo.KAMIKAZE){
            kamikaze(origen,dest,lists,new ArrayList<>());
        }else{
            multishot(origen,dest,lists,new ArrayList<>());
        }
        return lists;
    }

    private void multishot(Nodo origen, Nodo destino, ArrayList<ArrayList<Nodo>> lists,List<Nodo> recorrido){
        origen.visitado = true;
        recorrido.add(origen);
        if(origen.equals(destino)){
            lists.add((ArrayList<Nodo>) recorrido);
            limpiarVisitados();
        }
        for (Arista a: origen.aritas) {
            if(!a.getDestino().visitado && a.activo){
                multishot(a.getDestino(),destino,lists,recorrido.subList(0,recorrido.size()));
            }
        }
    }

    private void kamikaze(Nodo origen, Nodo destino, ArrayList<ArrayList<Nodo>> lists, List<Nodo> recorrido){
        origen.visitado = true;
        recorrido.add(origen);
        if(origen.equals(destino)){
            lists.add((ArrayList<Nodo>) recorrido);
            limpiarVisitados();
        }
        for (Arista a: origen.aritas) {
            if(!a.getDestino().visitado){
                kamikaze(a.getDestino(),destino,lists,recorrido.subList(0,recorrido.size()));
            }
        }
    }

    public ArrayList<Nodo> recorridoDijkstra(Nodo origen, String destino){
        ArrayList<ArrayList<Nodo>> lists = new ArrayList<>();
        Nodo dest = buscarNodo(destino);
        kamikaze(origen,dest,lists,new ArrayList<>());
        int camino = Integer.MAX_VALUE;
        ArrayList<Nodo> result = new ArrayList<>();
        for(ArrayList<Nodo> recorrido: lists){
            int peso = calcula(recorrido);
            if(peso < camino){
                camino = peso;
                result = recorrido;
            }
        }
        return result;
    }

    public void setConfiguraciones(int activarArista,int meditacion,int plataInicial,
                                   int segundosAristas,int valorAristas){
        configuraciones[0] = activarArista;
        configuraciones[1] = meditacion;
        configuraciones[2] = plataInicial;
        configuraciones[3] = segundosAristas;
        configuraciones[5] = valorAristas;
        for (Nodo i: vertices) {
            i.setSegundosArista(segundosAristas);
            i.setMeditacion(meditacion);
        }

    }



    private int calcula(List<Nodo> recorrido){
        ArrayList<Arista> aristas = new ArrayList<>();
        int result = 0;
        for(int i = 0; i < recorrido.size() - 1; i++){
            Nodo a = recorrido.get(i);
            aristas.add(a.buscarArista(recorrido.get(i+1)));
        }
        for(Arista a: aristas){
           result += a.getPeso();
        }
        return result;
    }

    public void
    dijkstra(String nodo){
        matrices.imprimeDijkstra(usernames.indexOf(nodo));
    }



}

package game.logic;

import game.logic.exceptions.AlreadyInsertedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grafo {

    public GrafoMatriz matrices;
    List<Nodo> vertices;
    List<Nodo> pila;
    List<String> usernames;

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
        Arista AtoB = new Arista(peso);
        AtoB.setDestino(destino);
        origen.agregarArista(AtoB);
        origen.totalPeso += peso;
        matrices.agregarArista(or,dest,peso);
    }

    private  Nodo buscarNodo(String id){
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


}

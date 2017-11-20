package game.settings;

import game.logic.exceptions.AlreadyInsertedException;
import game.gui.Gui;
import game.logic.Grafo;

public class CreadorGrafo {




    public static void main(String[] args) {
        int i;
        Grafo grafo = new Grafo();
        do{
            i = Gui.menu("Configuraciones iniciales",new String[]{"Ingresar un nodo",
                    "Ingresar una Arista", "imprimir","Borrar nodo","Comenzar partida"});
            switch (i){
                case 0:
                    String id = Gui.input("Ingresar un Nodo","ingrese el " +
                            "nombre del nodo");
                    try {
                        grafo.insertarVertice(id);
                    }catch (AlreadyInsertedException e){
                        System.out.println("Nodo previamente insertado");
                    }
                    break;
                case 1:
                    String nodoA = Gui.input("Ingresar una Arista",
                            "Nombre de Nodo Origen: ");
                    String nodoB = Gui.input("","Nodo destino: ");
                    int peso = Integer.parseInt(Gui.input("","Peso de arista"));
                    try{
                        grafo.insertarArista(nodoA,nodoB,peso);
                    }catch (AlreadyInsertedException e){
                        System.out.println("Arista previamente insertado");
                    }
                    break;
                case 2:
                    grafo.imprimir();
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }while(true);
    }

}

package game.logic.Estructuras;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author gaboq
 */
public class GrafoMatriz {
    
    private int max;
    private int[][] matriz = new int[100][100];
    private int[][] matrizAdyacencia = new int[100][100];
    private String[] vertices = new String[100];
    private boolean[] visitados = new boolean[100];
    private int cantidadVertices;

    public GrafoMatriz() {
        max = 100;
        cantidadVertices = 0;
        for (int i=0; i<100; i++)
        {
            vertices[i] = "";
            visitados[i] = false;

            // para incializar matriz
            for (int j = 0; j < 100; j++)
            {
                matriz[i][j] = 0;
                matrizAdyacencia[i][j] = 0;
            }
        }
    }

    boolean esConexo(){
        int[][] array = warshall_floyd();
        for (int i = 0; i < cantidadVertices; i++)
            for(int j = 0; j < cantidadVertices; j++){
                if(array[i][j] == 9999){
                    return false;
                }
            }
        return true;
    }
    

    // agregar vertice
    public void agregarVertice(String v)
    {
        // si hay campo y v no est'a en el grafo
        if (cantidadVertices < max && indexOfVertice(v) == -1)
        {
            vertices[cantidadVertices++] = v;
        }
    }

    // index de un vertice
    public int indexOfVertice(String v)
    {
        for (int i = 0; i < cantidadVertices; i++) {
            if (Objects.equals(vertices[i], v))
                return i;
        }

        return -1;
    }

    // agregar arista
    void agregarArista(String origen, String destino, int valor)
    {
        int orig = indexOfVertice(origen);
        int dest = indexOfVertice(destino);

        if (orig != -1 && dest != -1){
            matriz[orig][dest] = valor;
            matrizAdyacencia[orig][dest] = 1;
        }
    }

    void desactivarArista(String origen, String destino){
        int orig = indexOfVertice(origen);
        int dest = indexOfVertice(destino);

        if (orig != -1 && dest != -1){
            matrizAdyacencia[orig][dest] = 0;
        }
    }


    void activarArista(String origen, String destino){
        int orig = indexOfVertice(origen);
        int dest = indexOfVertice(destino);

        if (orig != -1 && dest != -1){
            matrizAdyacencia[orig][dest] = 1;
        }
    }

    // imprimir
    public void imprimir()
    {
        System.out.print("\t");
        for (int i = 0; i < cantidadVertices; i++) {
            System.out.print(vertices[i] + "\t");
        }
        System.out.print("\n");
        for (int i = 0; i < cantidadVertices; i++) {
            System.out.print(vertices[i] + "\t");
            for (int j = 0; j < cantidadVertices; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }


    //limpiar visitados
    public void limpiarVisitados()
    {
        for (int i=0; i < cantidadVertices;i++)
            visitados[i] = false;
    }

    // busca los nodos adyacentes y los devuelve en un vector
    public ArrayList<String> buscarAdyacientes(String n) {
        ArrayList<String> vec = new ArrayList<String>();
        int node = indexOfVertice(n);

        for (int i = 0; i < cantidadVertices; i++) {
            if (matriz[i][node] != 0) {
                vec.add(vertices[i]);
            }
        }

        return vec;
    }
    
    public String getVertice(int indice) {
        return vertices[indice];
    }

    public  void imprimeDijkstra(int indice){
        dijkstra(matriz,indice);
    }
    
    private void dijkstra(int[][] grafo, int src) {
        int V = vertices.length;
        int[] dist = new int[V];
        // dist[i] guarda la distancia mas corta desde src hasta el vertice i

        boolean[] verticeYaProcesado = new boolean[V]; 
        //Este arreglo tiene true si el vertice i ya fue procesado

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
           dist[i] = Integer.MAX_VALUE;
           verticeYaProcesado[i] = false;
        }
        // La distancia del vertice origen hacia el mismo es siempre 0
        dist[src] = 0;

        //Encuentra el camino mas corto para todos los vertices
        for (int count = 0; count < V-1; count++)
        {

          //Toma el vertice con la distancia minima del cojunto de vertices aun no procesados
          //En la primera iteracion siempre se devuelve src
          int u = minDistance(dist, verticeYaProcesado);

          // Se marca como ya procesado
          verticeYaProcesado[u] = true;

          // Update dist value of the adjacent vertices of the picked vertex.
          for (int v = 0; v < V; v++)

            //Se actualiza la dist[v] solo si no esta en verticeYaProcesado, hay un
            //arco desde u a v y el peso total del camino desde src hasta v a traves de u es 
            // mas pequeno que el valor actual de dist[v]
            if (!verticeYaProcesado[v] && grafo[u][v] > 0 && dist[u] != Integer.MAX_VALUE 
                                          && dist[u]+grafo[u][v] < dist[v])
               dist[v] = dist[u] + grafo[u][v];
        }

        // se imprime el arreglo con las distancias
        printSolution(dist, V);
    }
    
    private int minDistance(int[] dist, boolean[] verticeYaProcesado) {
        
        int min = Integer.MAX_VALUE; int min_index=0;

        for (int v = 0; v < vertices.length; v++)
          if (!verticeYaProcesado[v] && dist[v] <= min) {
              min = dist[v];
              min_index = v;
           }
        return min_index;
    }
    
    private void printSolution(int[] dist, int n) {
        System.out.println("Distancia del vertice desde el origen\n");
        for (int i = 0; i < vertices.length; i++)
           System.out.println(i + " \t\t " + dist[i]);
    }

    private int[][] warshall_floyd(){
        int cn = cantidadVertices;
        int path[][] = new int[100][100];

        for (int i = 0; i < cn; i++) {
            for (int j = 0; j < cn; j++) {
                if (matriz[i][j] > 0)//peso
                    path[i][j] = matriz[i][j];
                else if (i==j)//diagonal
                    path[i][j] = 0;
                else // no hay -> infinito
                    path[i][j] = 9999;
                //MAX_VALUE;
            }
        }
        for(int k = 0; k < cn; k++)
            for(int i = 0; i < cn; i++)
                for(int j = 0; j < cn; j++){
                    int dt = path[i][k] + path[k][j];
                    if(path[i][j] > dt)
                    {
                        path[i][j] = dt;
                    }
                }
        return path;
    }

    ArrayList<String> profundidad(int verticeActual, String buscado, ArrayList<String> caminoRecorrido){
        ArrayList<String> resultado = new ArrayList<>();
        visitados[verticeActual] = true;
        for(int i = 0; i < cantidadVertices; i++){
            if(matrizAdyacencia[verticeActual][i] != 0 && !visitados[i]){
                if(Objects.equals(buscado, vertices[i])){
                    caminoRecorrido.add(vertices[verticeActual]);
                    caminoRecorrido.add(buscado);
                    resultado.add("1");
                    resultado.add("lel");
                    resultado.add("correcaminos");
                    break;
                }
                else{
                    caminoRecorrido.add(vertices[verticeActual]);
                    resultado = profundidad( i , buscado , caminoRecorrido);
                    if(resultado.size() == 3){
                        break;
                    }
                }
            }
        }
        limpiarVisitados();
        return caminoRecorrido;
    }



}

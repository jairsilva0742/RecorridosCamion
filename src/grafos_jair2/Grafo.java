/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos_jair2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Leonardo
 */
public class Grafo {
    private final int [][] matrizAdy;
    private int [] NecesidadesEstaciones;
    int NecesidadEstacion;
    int valorCamion=0;
     StringBuilder ruta=new StringBuilder();
    /**
     * El constructor de grafo crea la matriz cuadrada de adyacencia con la 
     * cantidad de Estaciones, tambien se crea el vector que contiene las 
     * necesidades de cada estación
     * @param cantidadEstaciones es el numero de estaciones
     */
    public Grafo(int cantidadEstaciones){
    matrizAdy= new int[cantidadEstaciones][cantidadEstaciones];
    NecesidadesEstaciones= new int[cantidadEstaciones];  
    }
    
    /**
     * El usuario indica si hay adyacencia entre dos estaciones y el programa 
     * adiciona 1 en la matriz de adyacencia
     * @param vi es el numero de la estacion 1
     * @param vj es el numero de la estacion 2
     */
    public void addAdyacencia(int vi, int vj){
    matrizAdy[vi][vj]=1;
    matrizAdy[vj][vi]=1;
    //
    
    }
    /**
     * este Método adiciona material a cada estacion, mediante el 
     * diligenciamiento del vector de Necesidades
     * @param vi es el indice del vector
     * @param material es la cantidad de material de la estacion vi
     */
    public void addMaterial(int vi,int material){
       NecesidadesEstaciones[vi]=material; 
        
    }
  
     
/**
 * Este Método contiene el método solicitado en el parcial, es un puente para 
 * poder usar el método recorrido camión, ya que datos como matrizAdy y 
 * NecesidadesEstaciones son atributos del Grafo y no pueden ser llamados desde
 * otro método.
 * @throws Exception 
 */
public void mostrarRuta() throws Exception{
    StringBuilder nuevaRuta=new StringBuilder();
    this.ruta=nuevaRuta;
    for(int i=0;i<NecesidadesEstaciones.length;i++){
    System.out.print(" En la pos "+i+" esta "+NecesidadesEstaciones[i]+"  ");
    }
    System.out.println(" ");
    recorridoCamion(matrizAdy,valorCamion,NecesidadesEstaciones);
}

/**
 * Esta es la clase principal solicitada en el parcial
 * @param matrizAdy es la matriz que contiene la adyacencia entre estaciones
 * @param valorInicialX es el valor de material que tiene el camión
 * @param necesidadEstaciones es un vector con las necesidades de cada una de
 * las estaciones
 * @return una cadena de string que contiene la ruta a seguir
 * @throws Exception 
 */
public String recorridoCamion (int[] [] matrizAdy, int valorInicialX, int [] necesidadEstaciones) throws Exception{
  
    dfs2(matrizAdy[0][0]);
    return ruta.toString(); 
}
/**
 * este método representa la metodología DFS para recorrer grafos. Este llama
 * al método DFSRecursivo
 * @param verticeInicio es el vertice de donde se inicia el recorrido
 * @throws Exception 
 */
public void dfs2 (int verticeInicio) throws Exception{
    if (verticeInicio >= matrizAdy.length) {
            throw new Exception("el vertice no existe");
        }
        int[] visitados = new int[matrizAdy.length];
        
        DFSRecursivo2(visitados, verticeInicio);
}

/**
 * En este método se obtiene de manera recursiva la ruta que va a recorrer el
 * camión teniendo en cuenta las necesidades de la estación asi como la cantidad 
 * de material que tiene el camión
 * @param visitados es el vector que indica si ya fue visitada la estación
 * @param v es el indice de la estación visitada
 * @throws Exception 
 */
private void DFSRecursivo2(int[] visitados, int v) throws Exception {
     ArrayList<Integer> adyacentes = new ArrayList<>();
     //si el vector es visitado se coloca 1
        visitados[v] = 1;
        /*acá se coloca obtiene el valor actual que tiene el camion teniendo en
        cuenta las necesidades satifechas de la estación visitada
        */
        valorCamion=valorCamion-NecesidadesEstaciones[v];
        System.out.println("Valor Camion: "+valorCamion);
        int p=0;
        //En el StringBuilder ruta se va agregando cada una de las estaciones
        ruta.append("Estacion "+v+" >> ");
        
        //Se crea una pila que almacenara las posiciones adyacentes de un vector
        Stack<Integer> posiciones=new Stack<Integer>();
        //En este primer For, capturo en la Pila todos los adyacentes del vector
        //que ya esta visitado
        for (int w = 0; w < matrizAdy.length; w++) {
            if (matrizAdy[v][w] == 1) {
            //la restricción para apilar, es que no haya sido visitado y que las
            //Necesidades en esa estación sean menores al valor actual del 
            //camión
                if (visitados[w] == 0&&NecesidadesEstaciones[w]<=valorCamion){
                posiciones.add(w);
                }
            }
            
        }
        System.out.println("Estamos en la estacion: "+v);
        int k=0;
        
        if(posiciones.size()>0){
            //Aca se llama un método que retorna la estacion con mayor necesidad
            //de la pila. el ID de la estacion se almacena en k
        k=mayorNecesidad(posiciones);
        
        System.out.println("La posicion con mayor necesidad es: "+k);}
        //Esta segundo for lo usamos para hacer recursividad del DFS
        for (int w = 0; w < matrizAdy.length; w++) {
            if (matrizAdy[v][w] == 1) {
                p=w;
                //aca se restringe a que no hayan sido visitadas las estaciones y
                //ademas que sea la estacion de mayor necesidad k
                if (visitados[p] == 0&&p==k) {
                    DFSRecursivo2(visitados, p);
                }
            }
            
        }
        
        
}

/**
 * En este método obtengo cual estación tiene la mayor necesidad, dentro de las
 * que fueron apiladas
 * @param posiciones es la pila con posiciones adyacentes al vector visitado
 * @return la posición de la estacion con mayor necesidad
 * @throws Exception 
 */
      public int mayorNecesidad(Stack posiciones)throws Exception{
          int k=0;int cont=0;
          int comparador=0;
          
          //Se inicia la comparación con la posicion 0 de la pila 
          comparador=NecesidadesEstaciones[Integer.parseInt(posiciones.get(0).toString())];
          
         while(cont<posiciones.size()){ 
          
          //se recorre cada una de las posiciones de la pila hasta determinar el mayor
          for(Object s:posiciones){
              int comparador2=NecesidadesEstaciones[Integer.parseInt(s.toString())];
              if(comparador2>comparador){
                  //cuando se enceuntra una estación con mayor necesidad que la
                  //varable de comparación inicial, se nombra esta como la 
                  //de comparación inicial
                  comparador=NecesidadesEstaciones[Integer.parseInt(s.toString())];
              }
          }cont++;
          //El ciclo se repite hasta que una de estas no entre al if anterior, y
          //hayan hecho todas las comparaciones, por eso se coloca el contador
          // como bandera en el while
            }
          System.out.println("Mayor: "+comparador);
          //una vez teniendo el valor de comparador, se obtiene que posicion
          //tiene la estacion de mayor necesidad.
         for(int i=0;i<NecesidadesEstaciones.length;i++){
                if(comparador==NecesidadesEstaciones[i]){
                    for(Object s:posiciones){
                    if(i==Integer.parseInt(s.toString())){
                    k=i;
                    
                    break;
                    }}
                }
            }
          System.out.println("");
          return k;
      }
      public void cambiarMaterial(int estacion, int material){
          NecesidadesEstaciones[estacion]=material;
          
      }

      /*
      desde acá se tienen métodos getters y setters de los distintos atributos
      */
    public int[][] getMatrizAdy() {
        return matrizAdy;
    }

    public int[] getNecesidadesEstaciones() {
        return NecesidadesEstaciones;
    }

    public void setValorCamion(int valorCamion) {
        this.valorCamion = valorCamion;
    }

    public void setNecesidadesEstaciones(int[] NecesidadesEstaciones) {
        this.NecesidadesEstaciones = NecesidadesEstaciones;
    }

    public int getNecesidadEstacion() {
        return NecesidadEstacion;
    }

    public void setNecesidadEstacion(int NecesidadEstacion) {
        this.NecesidadEstacion = NecesidadEstacion;
    }
      
}

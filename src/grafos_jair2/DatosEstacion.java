/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos_jair2;



/**
 *
 * @author 57300
 */
public class DatosEstacion {

    Estacion[] estaciones;
    private Grafo grafo;

    public DatosEstacion(int c) {
        estaciones = new Estacion[c];
        grafo = new Grafo(c);
        
        for(int i=0;i<estaciones.length;i++){
            Estacion nueva=new Estacion(i);
            estaciones[i]=nueva;
            
        }
    }

  

    public Estacion[] getEstaciones() {
        return estaciones;
    }

 

    public Grafo getGrafo() {
        return grafo;
    }

   public Estacion getEstacion(int i){
    return estaciones[i];
}


}

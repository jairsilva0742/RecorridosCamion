/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos_jair2;

import java.awt.geom.Ellipse2D;

/**
 *
 * @author Leonardo
 */
public class Estacion {
  private Ellipse2D forma;
 private final int id;

    public int getId() {
        return id;
    }


   
      
      public Estacion(int id){
      
      this.id=id;    
       
    }

    

    public Ellipse2D getForma() {
        return forma;
    }

    public void setForma(Ellipse2D forma) {
        this.forma = forma;
    }

    

}

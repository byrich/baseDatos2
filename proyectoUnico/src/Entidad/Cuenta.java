/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

/**
 *
 * @author Byrich
 */
public class Cuenta {
    public int id;
    public double cantidad;
    public long dpi;

    public Cuenta(int id, double cantidad, long dpi) {
        this.id = id;
        this.cantidad = cantidad;
        this.dpi = dpi;
    }

    
    
    
    @Override
    public String toString(){
        return String.format("No Cuenta: %s, Cantidad: %f",this.id, this.cantidad);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva.operadores;

import evolutiva.Individuo;

/**
 *
 * @author Cristian
 */
public abstract class OperadorMutacion {
    
    private double valorProb;

    public OperadorMutacion(double valorProb) {
        this.valorProb = valorProb;
    }
    
    public boolean realizarMutacion(){
        if (Math.random()< valorProb)
            return true;
        else
            return false;
    }
    
    public abstract Individuo mutar2(Individuo i);
    
    public Individuo mutar(Individuo i){
        if(realizarMutacion())
            return mutar2(i);
        else 
            return i;
    }
}

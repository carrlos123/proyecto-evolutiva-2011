/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva.operadores;

import evolutiva.Individuo;
import java.util.ArrayList;

/**
 *
 * @author Cristian
 */
public abstract class OperadorCruce {
    private double probabilidad;
    
    public OperadorCruce(double probabilidad){
        this.probabilidad = probabilidad;
    }
    
    public boolean realizarCruce(){
        if (Math.random()< probabilidad)
            return true;
        else
            return false;
    }
    
    public abstract ArrayList<Individuo> cruzar2(Individuo p1, Individuo p2);
    
    public ArrayList<Individuo> cruzar(Individuo p1, Individuo p2){
        if(realizarCruce()){
            return cruzar2(p1, p2);
        }
        else{
            ArrayList<Individuo> padresSinCruzar = new ArrayList<Individuo>();
            padresSinCruzar.add(p1);
            padresSinCruzar.add(p2);
            return padresSinCruzar;
        }
    }
}

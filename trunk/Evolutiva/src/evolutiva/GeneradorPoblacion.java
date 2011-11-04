/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva;


/**
 *
 * @author Cristian
 */
public abstract class GeneradorPoblacion {
    
    public abstract Individuo generarIndividuoRamndon();
    
    public abstract Poblacion generar(int p);
    
}

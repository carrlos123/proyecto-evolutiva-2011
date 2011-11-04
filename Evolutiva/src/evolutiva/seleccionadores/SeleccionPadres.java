/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva.seleccionadores;
import evolutiva.Individuo;
import evolutiva.Poblacion;
/**
 *
 * @author Cristian
 */
public abstract class SeleccionPadres {
    
    public abstract Individuo getPadre(Poblacion p);
    
}

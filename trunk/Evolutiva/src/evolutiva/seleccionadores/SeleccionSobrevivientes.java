/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva.seleccionadores;

import evolutiva.Poblacion;

/**
 *
 * @author Cristian
 */
public abstract class SeleccionSobrevivientes {
    
    public abstract  Poblacion getSobrevivientes(Poblacion vieja, Poblacion nueva);
}

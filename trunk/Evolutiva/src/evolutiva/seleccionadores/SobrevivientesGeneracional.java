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
public class SobrevivientesGeneracional extends SeleccionSobrevivientes{

    public SobrevivientesGeneracional() {
    }
    
    @Override
    public Poblacion getSobrevivientes(Poblacion vieja, Poblacion nueva) {
        return nueva; //reemplaza toda la poblacion vieja por la nueva.
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva.seleccionadores;

import evolutiva.Individuo;
import evolutiva.Poblacion;
import java.util.List;

/**
 *
 * @author Cristian
 */
public class SobrevivientesStadyState extends SeleccionSobrevivientes{
    
    private float porcentaje;

    public SobrevivientesStadyState(float valor) {
        this.porcentaje = valor;
    }

    
    @Override
    public Poblacion getSobrevivientes(Poblacion vieja, Poblacion nueva) {
        List <Individuo> temp;
        int cantViejo = (int)(vieja.getTamanio()* porcentaje);
        temp = vieja.getMejoresIndividuos(cantViejo);
        temp.addAll(nueva.getMejoresIndividuos(nueva.getTamanio() - cantViejo));
        return new Poblacion(temp);
    }
    
}

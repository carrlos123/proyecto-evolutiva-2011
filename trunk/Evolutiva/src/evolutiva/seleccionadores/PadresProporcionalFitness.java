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
public class PadresProporcionalFitness extends SeleccionPadres{

    @Override
    public Individuo getPadre(Poblacion p) {
      float elementoAleatorio =(float) Math.random() * p.getValorFitnessTotal();
      int posElemento = 0;
      p.ordenarPorFitness();
      float acumulado = 0.0f;
      while (acumulado < elementoAleatorio){
          acumulado+= p.getIndividuo(posElemento).getValorFitness();
          posElemento++;
      }
      return p.getIndividuo(posElemento);
    }


    
}

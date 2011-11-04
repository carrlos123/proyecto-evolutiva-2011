/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva.operadores;

import evolutiva.Individuo;
import java.util.Vector;

/**
 *
 * @author Cristian
 */

public class MutacionPorIntercambio extends OperadorMutacion{

    public MutacionPorIntercambio(double valorProb) {
        super(valorProb);
    }

    @Override
    public Individuo mutar2(Individuo i) {
        Vector <Integer> genes = i.getGenes(); //extraemos los genes del individuo
        int posA, posB;
        //calculo las posiciones a intercambiar
        posA = (int) (Math.random() * genes.size()); 
        posB = (int) (Math.random() * genes.size());
        //System.out.println("pos a " + posA+ "  posB  "+ posB); 
        //guardo uno de los valores
        int Aux = genes.elementAt(posB);
        //realizo el intercambio
        genes.set(posB, genes.elementAt(posA));
        genes.set(posA, Aux);
        //retorno un individuo nuevo mutado
        return new Individuo(genes);
    }
    
}

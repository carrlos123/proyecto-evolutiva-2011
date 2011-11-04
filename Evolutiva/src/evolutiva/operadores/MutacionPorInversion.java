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
public class MutacionPorInversion extends OperadorMutacion{

    public MutacionPorInversion(double valorProb) {
        super(valorProb);
    }

    @Override
    public Individuo mutar2(Individuo i) {
            //extraemos los genes del individuo
        Vector <Integer> genes = i.getGenes(); 
        int min, max, aux;
        do{ //calculo dos posiciones que tengan mas de un numero de distancia
            min = (int) (Math.random() * genes.size()); 
            max = (int) (Math.random() * genes.size());
        }while((Math.abs(min - max) > genes.size()-2)||(Math.abs(min - max) < 2));
        if (max < min){
            aux = min;
            min = max;
            max = aux;
        }
        while (min < max){
            //guardo el valor de la posMayor
            aux = genes.elementAt(max);
            //intercambio
            genes.set(max, genes.elementAt(min));
            genes.set(min, aux);//almaceno el valor auxiliar.
            //movemos los indices de menor y mayor.
            min++;
            max--;
        }
        //retorno el individuo nuevo mutado
        return new Individuo(genes);

    }    
}

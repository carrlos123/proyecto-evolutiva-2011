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
public class MutacionPorInsercion extends OperadorMutacion{

    public MutacionPorInsercion(double valorProb) {
        super(valorProb);
    }

    @Override
    public Individuo mutar2(Individuo i) {   
        //extraemos los genes del individuo
        Vector <Integer> genes = i.getGenes(); 
        int pos1, pos2, aux;
        do{ //calculo dos posiciones que tengan mas de un numero de distancia
            pos1 = (int) (Math.random() * genes.size()); 
            pos2 = (int) (Math.random() * genes.size());
        }while((Math.abs(pos1 - pos2) > genes.size()-2)||(Math.abs(pos1 - pos2) < 2));
        if (pos2 < pos1){
            aux = pos1;
            pos1 = pos2;
            pos2 = aux;
        }
        //guardo el valor de la posMayor
        aux = genes.elementAt(pos2);
        //System.out.println(pos1 +"   "+pos2);
        //realizo el intercambio de atraz para adelante.
        for (int j = pos2; j > pos1; j--){
            genes.set(j, genes.elementAt(j-1));
        }
        //inserto el valor Auxiliar en la proxima pos de posMenor
        //realizo la insercion, reemplazando un valor que esta repetido.
        genes.set(pos1, aux);
        //retorno el nuevo individuo generado a partir de la nueva lista de genes
        return new Individuo(genes);
    }
    
    
}

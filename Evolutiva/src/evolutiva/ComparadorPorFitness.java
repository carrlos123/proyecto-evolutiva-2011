/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva;

import java.util.Comparator;

/**
 *
 * @author Cristian
 */
class ComparadorPorFitness implements Comparator {
    
    public int compare(Object o1, Object o2) {
        Individuo i1 = (Individuo) o1;
        Individuo i2 = (Individuo) o2;
        if (i1.getValorFitness() < i2.getValorFitness()) {
            return 1;
        } else if (i1.getValorFitness() > i2.getValorFitness()) {
            return -1;
        }
        return 0;
    }

    
}
